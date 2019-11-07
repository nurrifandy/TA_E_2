package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.service.BukuService;


@Controller
@RequestMapping("/book")
public class BukuController{
    @Qualifier("bukuServiceImpl")

    @Autowired
    private BukuService bukuService;

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
    
}