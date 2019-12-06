package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.JenisBukuModel;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.service.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/book")
public class BukuController{
    @Qualifier("bukuServiceImpl")

    @Autowired
    private BukuService bukuService;

    @Autowired
    private JenisBukuService jenisBukuService;

    @Autowired
    private PeminjamanBukuService peminjamanBukuService;

    @PostMapping("/update/{id}")
    public String submitUpdateBook(@PathVariable long id,@ModelAttribute BukuModel book, Model model, RedirectAttributes directModel){
        BukuModel buku = bukuService.getBukuByIdBuku(id).get();
        BukuModel updateBook = null;
        Boolean isSuccess = false;
        Boolean isGagal = true;
        String message = "Update Gagal!";
        if(book.getJumlah()>=(buku.getJumlah()-bukuService.availableBook(buku))){
            updateBook = bukuService.updateBook(book);
            isSuccess = true;
            isGagal = false;
            message = "Update Berhasil!";
        }
        
        directModel.addFlashAttribute("book", updateBook);
        directModel.addFlashAttribute("message", message);
        directModel.addFlashAttribute("isBerhasil", isSuccess);
        directModel.addFlashAttribute("isGagal", isGagal);
        return "redirect:/book/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuku(@PathVariable Long id, Model model, RedirectAttributes directModel) {
        BukuModel existingBook = bukuService.findByIdBook(id);
        model.addAttribute("book", existingBook);
        bukuService.deleteBook(existingBook);
        return "redirect:/book/detail";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String formAddBook(Model model) {
        BukuModel newBook = new BukuModel();
        List<JenisBukuModel> listJenisBuku = jenisBukuService.getJenisBukuList();
        model.addAttribute("book", newBook);
        model.addAttribute("jenisBuku", listJenisBuku);
        return "book/form-add-book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitAddBook(@RequestParam("idJenis") Long id,
                                @RequestParam("judul") String judul,
                                @RequestParam("pengarang") String pengarang,
                                @ModelAttribute BukuModel book, Model model) {
        JenisBukuModel jenis = jenisBukuService.getJenisByIdJenis(id).get();
        book.setJenisBuku(jenis);
        List<BukuModel> bukuModelList = bukuService.getListBuku();
        if (bukuModelList != null){
            for (BukuModel i : bukuModelList){
                if(i.getJudul().toLowerCase().equals(judul.toLowerCase()) && i.getPengarang().toLowerCase().equals(pengarang.toLowerCase())) {

                    List<JenisBukuModel> listJenisBuku = jenisBukuService.getJenisBukuList();
                    model.addAttribute("jenisBuku", listJenisBuku);
                    model.addAttribute("gagal", true);
                    return "book/form-add-book";
                }
            }
        }
        bukuService.addBook(book);
        List<JenisBukuModel> listJenisBuku = jenisBukuService.getJenisBukuList();
        model.addAttribute("jenisBuku", listJenisBuku);
        model.addAttribute("namaBuku", judul);
        model.addAttribute("added", true);
        return "book/form-add-book";
    }

    @RequestMapping("/detail")
    public String bookDetails(Model model){
        List<BukuModel> bookList = bukuService.getListBuku();
        List<Integer> bookSum = new ArrayList<Integer>();
        // bukuService.availableBook(bookList, bookSum);
        for (BukuModel book:bookList){
            int available = bukuService.availableBook(book);
            bookSum.add(Integer.valueOf(available));
        }
        
        model.addAttribute("bookList", bookList);
        model.addAttribute("available", bookSum);
        return "book/view-detail";
    }

    /**
     * api untuk overdue book
     * 
     * @param model
     * @return
     */
    @PostMapping(value="/overdue")
    public String postMethodName(Model model) {
        //TODO: process POST request
        return "";
    }
    
    
}