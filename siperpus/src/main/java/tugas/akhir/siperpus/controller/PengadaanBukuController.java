package tugas.akhir.siperpus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.service.PengadaanBukuService;

@Controller
@RequestMapping("/procurement")
public class PengadaanBukuController {
    @Autowired
    PengadaanBukuService pengadaanBukuService;

    @GetMapping(value = "/add")
    public String formAddProcurement(Model model) {
        return "procurement/form-add-procurement";
    }

    @PostMapping(value = "add")
    public String submitAddProcurement(@ModelAttribute PengadaanBukuModel pengadaan, Model model) {

        int status = 0;
        if (false/** user.getRole().getNama().equals("Pustakawan") */
        ) {
            status = 1;
        }

        // Mono<AnggotaDetailModel> koperasi = pengadaanBukuService.getAnggotaDetail();

        Boolean anggotaKoperasi = false;
        if (anggotaKoperasi) {
            status = 3;
        }
        // set pengadaan untuk user
        pengadaan.setStatus(status);

        pengadaanBukuService.addProcurement(pengadaan);
        model.addAttribute("pengadaan", pengadaan);
        return "procurement/add-procurement-submit";
    }

    @GetMapping("/view")
    public String displayAllProcurement(Model model) {
        /**
         * if(){
         * 
         * }else if(){
         * 
         * }else if{
         * 
         * }
         */

        List<PengadaanBukuModel> listPengadaan = pengadaanBukuService.getListPengadaan();
        model.addAttribute("listPengadaan", listPengadaan);
        return "procurement/view-procurement";
    }
}