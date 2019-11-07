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

import tugas.akhir.siperpus.model.BookModel;
import tugas.akhir.siperpus.model.TypeOfBookModel;
import tugas.akhir.siperpus.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController{
    @Qualifier("bookServiceImpl")

    @Autowired
    private BookService bookService;

    @GetMapping("/update/{id}")
    public String formUpdateBook(@PathVariable long id, Model model){
        BookModel book = bookService.findByIdBook(id);
        model.addAttribute("book", book);
        return "book/form-update-book";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateBook(@PathVariable long id,@ModelAttribute BookModel book, Model model){
        BookModel updateBook = bookService.updateBook(book);
        model.addAttribute("book", updateBook);
        return "book/form-update-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuku(@PathVariable Long id, Model model) {
        BookModel existingBook = bookService.findByIdBook(id);
        model.addAttribute("book", existingBook);
        bookService.deleteBook(existingBook);
        return null;
    }
    
}