package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.BookModel;

public interface BookService{
    BookModel findByIdBook(long id);

    BookModel updateBook(BookModel book);

    void deleteBook(BookModel book);
}