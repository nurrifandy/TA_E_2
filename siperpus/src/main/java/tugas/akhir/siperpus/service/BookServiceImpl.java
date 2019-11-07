package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.akhir.siperpus.model.BookModel;
import tugas.akhir.siperpus.repository.BookDb;

@Service
@Transactional

public class BookServiceImpl implements BookService{
    @Autowired
    private BookDb bookDb;

    @Override
    public BookModel findByIdBook(long id){
        return bookDb.findById(id).get();
    }

    @Override
    public BookModel updateBook(BookModel book){
        BookModel currBook = bookDb.findById(book.getId()).get();
        try{
            currBook.setJumlah(book.getJumlah());
            bookDb.save(currBook);
            return currBook;
        }catch (NullPointerException e){
            return null;
        }

    }

    @Override
    public void deleteBook(BookModel book){
        bookDb.deleteById(book.getId());
    }

}