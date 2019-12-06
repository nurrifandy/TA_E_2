package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.service.BukuService;

import tugas.akhir.siperpus.model.SuratDetailModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.service.PeminjamanBukuService;
import tugas.akhir.siperpus.service.SuratRestService;
import tugas.akhir.siperpus.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/loan")
public class PeminjamanBukuController {

    @Autowired
    PeminjamanBukuService peminjamanBukuService;

    @Autowired
    BukuService bukuService;

    @Autowired
    SuratRestService suratRestService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/view")
    public String view(Model model){
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<PeminjamanBukuModel> myListGuru = new ArrayList<>();
        List<PeminjamanBukuModel> myListSiswa = new ArrayList<>();
        List<PeminjamanBukuModel> listPeminjaman = peminjamanBukuService.getPeminjamanList();
        if(user.getRole().getNama().toLowerCase().equals("pustakawan")) {
            model.addAttribute("peminjamanList", listPeminjaman);
            return "loan/view-loan";
        } else {
            if(user.getRole().getNama().toLowerCase().equals("siswa")) {
                for (PeminjamanBukuModel i : listPeminjaman) {
                    if (i.getUser().getRole().getNama().toLowerCase().equals("siswa")) {
                        if (myListSiswa.contains(i)) {
                            continue;
                        } else {
                            myListSiswa.add(i);
                            model.addAttribute("peminjamanList", myListSiswa);
                            return "loan/view-loan";
                        }
                    }
                }
            } else {
                for (PeminjamanBukuModel i : listPeminjaman) {
                    if (i.getUser().getRole().getNama().toLowerCase().equals("guru")) {
                        if (myListGuru.contains(i)) {
                            continue;
                        } else {
                            myListGuru.add(i);
                            model.addAttribute("peminjamanList", myListGuru);
                            return "loan/view-loan";
                        }
                    }
                }
            }
        }
        return "loan/view-loan-fail";
    }

    @GetMapping("/overdue/{idPeminjaman}")
    public String makeMail(@PathVariable long idPeminjaman, Model model){
        PeminjamanBukuModel peminjaman = peminjamanBukuService.findLoanByIdLoan(idPeminjaman);
        UserModel user = peminjaman.getUser();
        int id = 1;
        String keterangan = "Peminjaman telah jatuh tempo, surat jatuh tempo harus dibuat";
        Date tanggal = new Date();
        String status = "Menunggu Persetujuan";
        String noSurat = "0";
        String usernameUser = user.getUsername();
        String password = user.getPassword();
        String message = suratRestService.postSurat(id,keterangan,tanggal,status,noSurat,usernameUser, password).block();
        if (message.equalsIgnoreCase("sukses")){
            message = "Surat peringatan untuk " + user.getUsername() + " berhasil dibuat!";
        }
        else if(message.equalsIgnoreCase("gagal")){
            message = "Surat peringatan untuk " + user.getUsername() + " gagal dibuat!";
        }
        model.addAttribute("message", message);
        return "redirect:loan/view";
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
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        ArrayList<PeminjamanBukuModel> listPeminjaman = new ArrayList<>();
		if (bukuService.availableBook(buku) > 0){
            PeminjamanBukuModel newLoan = new PeminjamanBukuModel();
            newLoan.setStatus(0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date today = new Date();
            dateFormat.format(today);
            String hari_ini = dateFormat.format(today);
        
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DATE, 7);
            
            Date seminggu = cal.getTime();
            
            newLoan.setTanggalPeminjaman(today);
    
            newLoan.setTanggalPengembalian(seminggu);
            newLoan.setUser(user);
            newLoan.setBuku(buku);
            listPeminjaman.add(newLoan);
            buku.setListPeminjaman(listPeminjaman);
            user.setListPeminjaman(listPeminjaman);
            peminjamanBukuService.addPeminjamanBuku(newLoan);
            model.addAttribute("hari_ini", hari_ini);
            model.addAttribute("buku", buku);
            return "loan/borrow-success";
        }
            
        else{
        return "loan/borrow-failed";
        }
        
    }
}
