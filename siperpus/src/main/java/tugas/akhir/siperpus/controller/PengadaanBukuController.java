package tugas.akhir.siperpus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.service.PengadaanBukuRestService;
import tugas.akhir.siperpus.service.PengadaanBukuService;
import tugas.akhir.siperpus.service.UserService;

@Controller
@RequestMapping("/procurement")
public class PengadaanBukuController{
    @Autowired
    PengadaanBukuService pengadaanBukuService;

    @Autowired
    PengadaanBukuRestService pengadaanBukuRestService;

    @Autowired
    UserService userService;

    @GetMapping(value="/add")
    public String formAddProcurement(Model model){
        return "procurement/form-add-procurement";
    }

    @PostMapping(value = "add")
    public String submitAddProcurement(@ModelAttribute PengadaanBukuModel pengadaan, Model model){

        int status = 0;
        if( false/**user.getRole().getNama().equals("Pustakawan")*/){
            status = 1;
        }
        
        //cari getAnggotaDetail(uuid) :)
        AnggotaDetailModel koperasi = pengadaanBukuRestService.getAnggotaDetail(1).block();
        
        Boolean anggotaKoperasi= false;
        if(koperasi.getIsPengurus() && koperasi.getTotalSimpanan() == 1000000){
            status = 3;
        }

        //set status pengadaan untuk user yang sedang login, dilakukan pengecekan terlebih dahulu
        pengadaan.setStatus(status);
        
        //uuid user yang sedang login
        UserModel user = userService.getUserByUuid("8a85de596e875f58016e8767eebe0000");
        //set user untuk mengisi uuid
        pengadaan.setUser(user);
        //set user terhadap pengadaan buku
        List<PengadaanBukuModel> listPengadaan = new ArrayList<>();
        listPengadaan.add(pengadaan);
        user.setListPengadaan(listPengadaan);
        
        pengadaanBukuService.addProcurement(pengadaan);
        model.addAttribute("pengadaan", pengadaan);
        return"procurement/add-procurement-submit";
    }

    @GetMapping("/view")
    public String displayAllProcurement(Model model){
        /**
         if(){

         }else if(){

         }else if{

         }
         */

        List<PengadaanBukuModel> listPengadaan = pengadaanBukuService.getListPengadaan();
        model.addAttribute("listPengadaan", listPengadaan);
        return"procurement/view-procurement";
    }

    @GetMapping("/delete/{id}")
    public String deleteProcurement(@PathVariable Long id, Model model) {
        PengadaanBukuModel existingProcurement = pengadaanBukuService.getProcurementById(id);
        model.addAttribute("procurement", existingProcurement.getJudul());
        if(existingProcurement.getStatus() == 0 || existingProcurement.getStatus() == 1){
            pengadaanBukuService.delete(existingProcurement);
            return "procurement/delete";
        }
        return "procurement/delete-fail";
    }

}