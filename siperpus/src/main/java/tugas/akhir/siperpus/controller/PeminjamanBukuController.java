package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "loan/view-loan";
    }

    @GetMapping("/update/{id}")
    public String formUpdateLoan(@PathVariable long id, Model model){
        PeminjamanBukuModel loan = peminjamanBukuService.findLoanByIdLoan(id);
        model.addAttribute("loan", loan);
        return "loan/form-update-loan";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateLoan(@PathVariable long id, @ModelAttribute PeminjamanBukuModel loan, Model model){
        PeminjamanBukuModel updateLoan = peminjamanBukuService.updateStatus(loan);
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        model.addAttribute("peminjamanList", listPeminjaman);
        model.addAttribute("loan", updateLoan);
        model.addAttribute("ubahStatus", true);
        return "loan/view-loan";
    }
}
