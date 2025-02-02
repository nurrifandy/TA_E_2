package tugas.akhir.siperpus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.service.PengadaanBukuRestService;
import tugas.akhir.siperpus.service.PengadaanBukuService;
import tugas.akhir.siperpus.service.UserService;

@Controller
@RequestMapping("/procurement")
public class PengadaanBukuController {
    @Autowired
    PengadaanBukuService pengadaanBukuService;

    @Autowired
    PengadaanBukuRestService pengadaanBukuRestService;

    @Autowired
    UserService userService;

    @GetMapping(value="/add")
    public String formAddProcurement(Model model){
        Boolean isSuccess = false;
        model.addAttribute("isSuccess", isSuccess);
        return "procurement/form-add-procurement";
    }

    @PostMapping(value = "/add")
    public String submitAddProcurement(@ModelAttribute PengadaanBukuModel pengadaan, Model model){
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        int status = 0;
        if( user.getRole().getNama().equals("Pustakawan")){
            status = 1;
        }

        
        //cari getAnggotaDetail(uuid) :) id sementara pada post man =402881e86e8ed64a016e8ed953b10000
        AnggotaDetailModel koperasi = new AnggotaDetailModel();
        koperasi = new AnggotaDetailModel();
        try{
            koperasi = pengadaanBukuRestService.getAnggotaDetail(user.getUuid()).block();
        }catch(Exception e){
            koperasi = null;
        }

        if(koperasi != null){
            if(user.getRole().getNama().equals("Guru") && koperasi.getIsPengurus() && koperasi.getTotalSimpanan() >= 1000000){
                status = 3;
            }
        }

        //set status pengadaan untuk user yang sedang login, dilakukan pengecekan terlebih dahulu
        pengadaan.setStatus(status);
        
        //uuid user yang sedang login
        //UserModel user = userService.getUserByUuid("8a85de596e875f58016e8767eebe0000");
        //set user untuk mengisi uuid
        pengadaan.setUser(user);
        
        //set user terhadap pengadaan buku
        List<PengadaanBukuModel> listPengadaan = new ArrayList<>();
        listPengadaan.add(pengadaan);
        user.setListPengadaan(listPengadaan);
        pengadaanBukuService.addProcurement(pengadaan);
        
        Boolean isSuccess = true;
        model.addAttribute("isSuccess", isSuccess);
        model.addAttribute("user", user);
        model.addAttribute("pengadaan", pengadaan);
        return "procurement/form-add-procurement";
    }

    @GetMapping("/view")
    public String displayAllProcurement(Model model){
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PengadaanBukuModel> listPengadaan = new ArrayList<>();
        if(user.getRole().getNama().equals("Pustakawan")){
            listPengadaan = pengadaanBukuService.getListPengadaan();
        }else{
            listPengadaan = user.getListPengadaan();
        }
        
        model.addAttribute("user", user);
        model.addAttribute("listPengadaan", listPengadaan);
        return "procurement/view-procurement";
    }

    @GetMapping("/delete/{id}")
    public String deleteProcurement(@PathVariable Long id, Model model) {
        PengadaanBukuModel existingProcurement = pengadaanBukuService.getProcurementById(id);
        model.addAttribute("procurement", existingProcurement.getJudul());
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getRole().getNama().toLowerCase().equals("pustakawan")) {
             if (existingProcurement.getStatus() == 0 || existingProcurement.getStatus() == 1) {
                pengadaanBukuService.delete(existingProcurement);
                return "redirect:/procurement/view";
            }
        } else {
            if (existingProcurement.getStatus() == 0) {
                pengadaanBukuService.delete(existingProcurement);
                return "redirect:/procurement/view";
            }
        }
        return "redirect:/procurement/view";
    }
}