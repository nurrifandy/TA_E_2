package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.JenisBukuModel;
import tugas.akhir.siperpus.service.*;

import java.util.List;


@Controller
@RequestMapping("/book")
public class BukuController{
    @Qualifier("bukuServiceImpl")

    @Autowired
    private BukuService bukuService;

    @Autowired
    private JenisBukuService jenisBukuService;

    @GetMapping("/update/{id}")
    public String formUpdateBook(@PathVariable long id, Model model){
        BukuModel book = bukuService.findByIdBook(id);
        model.addAttribute("book", book);
        return "book/form-update-book";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateBook(@PathVariable long id,@ModelAttribute BukuModel book, Model model){
        BukuModel updateBook = bukuService.updateBook(book);
        model.addAttribute("book", updateBook);
        return "book/form-update-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuku(@PathVariable Long id, Model model) {
        BukuModel existingBook = bukuService.findByIdBook(id);
        model.addAttribute("book", existingBook);
        bukuService.deleteBook(existingBook);
        return "book/delete-book";
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
    public String submitAddBook(@RequestParam("id") Long id,
                                @RequestParam String judul,
                                @RequestParam String pengarang,
                                @ModelAttribute BukuModel buku, Model model) {
        JenisBukuModel jenis = jenisBukuService.getJenisByIdJenis(id).get();
        buku.setJenisBuku(jenis);
        List<BukuModel> bukuModelList = bukuService.getListBuku();
        if (bukuModelList != null){
            for (BukuModel i : bukuModelList){
                if(i.getJudul().equals(judul) && i.getPengarang().equals(pengarang)) {
                    return "book/add-book-fail";
                }
            }
        }
        bukuService.addBook(buku);
        model.addAttribute("namaBuku", judul);
        return "book/add-book-submit";
    }



}