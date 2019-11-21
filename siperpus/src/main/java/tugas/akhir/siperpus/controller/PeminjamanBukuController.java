package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.model.SuratDetailModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.service.PeminjamanBukuService;
import tugas.akhir.siperpus.service.SuratRestService;
import tugas.akhir.siperpus.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/loan")
public class PeminjamanBukuController {

    @Autowired
    PeminjamanBukuService peminjamanBukuService;

    @Autowired
    SuratRestService suratRestService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/view")
    public String view(Model model){
        Optional<UserModel> user = userService.getUserByNama(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PeminjamanBukuModel> myListGuru = new ArrayList<>();
        List<PeminjamanBukuModel> myListSiswa = new ArrayList<>();
        if(user.get().getRole().getNama().toLowerCase().equals("pustakawan")) {
            List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
            model.addAttribute("peminjamanList", listPeminjaman);
            return "loan/view-loan";
        } else {
            List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
            for(PeminjamanBukuModel  i : listPeminjaman) {
                if (i.getUser().getRole().getNama().equals("siswa")) {
                    if (myListSiswa.contains(i)) {
                        continue;
                    } else {
                        myListSiswa.add(i);
                        model.addAttribute("peminjamanList", myListSiswa);
                        return "loan/view-loan";
                    }
                } else if (i.getUser().getRole().getNama().equals("guru")) {
                    if (myListGuru.contains(i)) {
                        continue;
                    } else {
                        myListGuru.add(i);
                        model.addAttribute("peminjamanList", myListGuru);
                        return "loan/view-loan";
                    }
                } else {
                    return "loan/view-loan-fail";
                }
            }
        }
        return "loan/view-loan-fail";
    }

    @GetMapping("/surat")
    public String makeMail(Model model){
        int id = 1;
        String keterangan = "Test semoga bisa";
        Date tanggal = new Date();
        String status = "Menunggu Persetujuan";
        String noSurat = "0";
        String usernameUser = "mirna";
        SuratDetailModel surat = suratRestService.postSurat(id,keterangan,tanggal,status,noSurat,usernameUser).block();
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
