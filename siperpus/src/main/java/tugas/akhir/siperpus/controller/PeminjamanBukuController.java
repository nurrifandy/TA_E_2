package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.repository.PeminjamanBukuDb;
import tugas.akhir.siperpus.service.BukuService;
import tugas.akhir.siperpus.service.PeminjamanBukuService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/loan")
public class PeminjamanBukuController {

    @Autowired
    PeminjamanBukuService peminjamanBukuService;

    @Autowired
    BukuService bukuService;

    @RequestMapping(value = "/view")
    public String view(Model model) {
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        model.addAttribute("peminjamanList", listPeminjaman);
        return "loan/view-loan";
    }

    @GetMapping("/update/{id}")
    public String formUpdateLoan(@PathVariable long id, Model model) {
        PeminjamanBukuModel loan = peminjamanBukuService.findLoanByIdLoan(id);
        model.addAttribute("loan", loan);
        return "loan/form-update-loan";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateLoan(@PathVariable long id, @ModelAttribute PeminjamanBukuModel loan, Model model) {
        PeminjamanBukuModel updateLoan = peminjamanBukuService.updateStatus(loan);
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        model.addAttribute("peminjamanList", listPeminjaman);
        model.addAttribute("loan", updateLoan);
        model.addAttribute("ubahStatus", true);
        return "loan/view-loan";
    }

    @RequestMapping("/add/{idBuku}")
    public String addLoan(@PathVariable long idBuku, Model model) {
        BukuModel buku = bukuService.getBukuByIdBuku(idBuku).get();
        if (buku.getJumlah() > 0){
            PeminjamanBukuModel newLoan = new PeminjamanBukuModel();
            peminjamanBukuService.addPeminjamanBuku(newLoan, idBuku);
            // model.addAttribute("hari_ini", hari_ini);
            model.addAttribute("buku", buku);
            return "loan/borrow-success";
        }
            
        else{ System.out.println("ini masuk ke else");
        return "loan/borrow-failed";
        }
        
    }
}
