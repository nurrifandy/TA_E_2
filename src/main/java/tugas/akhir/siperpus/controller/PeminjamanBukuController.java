package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                        myListSiswa.add(i);
                        model.addAttribute("peminjamanList", myListSiswa);
                    }
                }
            } else {
                for (PeminjamanBukuModel i : listPeminjaman) {
                    if (i.getUser().getRole().getNama().toLowerCase().equals("guru")) {
                        myListGuru.add(i);
                        model.addAttribute("peminjamanList", myListGuru);
                    }
                }
            }
        }
        return "loan/view-loan";
    }

    @GetMapping("/overdue/{idPeminjaman}")
    public String makeMail(@PathVariable long idPeminjaman,RedirectAttributes directModel, Model model){
        PeminjamanBukuModel peminjaman = peminjamanBukuService.findLoanByIdLoan(idPeminjaman);
        UserModel user = peminjaman.getUser();

        Boolean isBerhasil = false;
        Boolean isGagal = false;
        int id = 1;
        
        String keterangan = "Peminjaman telah jatuh tempo, surat jatuh tempo harus dibuat";
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatDate.format(new Date());
        String status = "Menunggu Persetujuan";
        String noSurat = "0";
        String usernameUser = user.getUsername();
        String password = "pacil54321";
    

        String message = "";
        try{
            message = suratRestService.postSurat(id,keterangan,date,status,noSurat,usernameUser, password).block();
        }catch(NullPointerException e){
            message = "gagal";
        }

        if(message ==  null){
            message = "";
        }

        if (message.equalsIgnoreCase("berhasil")){
            message = "Surat peringatan untuk " + user.getUsername() + " berhasil dibuat!";
            isBerhasil=true;
        }
        else if(message.equalsIgnoreCase("gagal")  || message.equalsIgnoreCase("")){
            message = "Surat peringatan untuk " + user.getUsername() + " gagal dibuat!";
            isGagal = true;
        }

        model.addAttribute("message", message);
        RedirectView  redirectView = new RedirectView("/loan/view");
        directModel.addFlashAttribute("isBerhasil", isBerhasil);
        directModel.addFlashAttribute("isGagal", isGagal);
        directModel.addFlashAttribute("message", message);
        return "redirect:/loan/view";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateLoan(@ModelAttribute PeminjamanBukuModel loan) {
        PeminjamanBukuModel updateLoan = peminjamanBukuService.updateStatus(loan);
        return "redirect:/loan/view";
    }

    @RequestMapping("/add/{idBuku}")
    public String addLoan(@PathVariable long idBuku, RedirectAttributes redirect) {
        BukuModel buku = bukuService.getBukuByIdBuku(idBuku).get();
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        ArrayList<PeminjamanBukuModel> listPeminjaman = new ArrayList<>();

        if (bukuService.availableBook(buku) > 0){
            PeminjamanBukuModel newLoan = new PeminjamanBukuModel();
            newLoan.setStatus(0);

            //loan Date
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date today = new Date();
            dateFormat.format(today);


            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DATE, 7);
            //return Date
            Date seminggu = cal.getTime();

            newLoan.setTanggalPeminjaman(today);
            newLoan.setTanggalPengembalian(seminggu);
            newLoan.setUser(user);
            newLoan.setBuku(buku);

            listPeminjaman.add(newLoan);
            buku.setListPeminjaman(listPeminjaman);
            user.setListPeminjaman(listPeminjaman);
            peminjamanBukuService.addPeminjamanBuku(newLoan);

            String hari_ini = dateFormat.format(today);
            redirect.addFlashAttribute("success", true);

        }

        else{
            redirect.addFlashAttribute("success", false);
        }

        return "redirect:/book/detail";

    }
}
