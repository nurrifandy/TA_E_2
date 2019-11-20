package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.model.SuratDetailModel;
import tugas.akhir.siperpus.service.PeminjamanBukuService;
import tugas.akhir.siperpus.service.SuratRestService;

import java.util.List;

@Controller
@RequestMapping("/loan")
public class PeminjamanBukuController {

    @Autowired
    PeminjamanBukuService peminjamanBukuService;

    @Autowired
    SuratRestService suratRestService;

    @RequestMapping(value = "/view")
    public String view(Model model){
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        model.addAttribute("peminjamanList", listPeminjaman);
        return "loan/view-loan";
    }

    @GetMapping("/surat")
    public String makeMail(Model model){
        SuratDetailModel surat = suratRestService.postSurat().block();
        model.addAttribute("surat", surat.getStatus());
        return "mail-sukses";
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
