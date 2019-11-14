package tugas.akhir.siperpus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.service.PengadaanBukuService;

@Controller
@RequestMapping("/procurement")
public class PengadaanBukuController{
    @Autowired
    PengadaanBukuService pengadaanBukuService;

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
}