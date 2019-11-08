package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.service.PeminjamanBukuService;

import java.util.List;

@Controller
@RequestMapping("/loan")
public class PeminjamanBukuController {

    @Autowired
    PeminjamanBukuService peminjamanBukuService;

    @RequestMapping(value = "/view")
    public String view(Model model){
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        model.addAttribute("peminjamanList", listPeminjaman);
        return "peminjaman/view-peminjaman";
    }
}
