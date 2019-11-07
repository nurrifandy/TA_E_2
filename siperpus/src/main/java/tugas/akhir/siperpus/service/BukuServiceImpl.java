package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.repository.BukuDb;

@Service
@Transactional

public class BukuServiceImpl implements BukuService{
    @Autowired
    private BukuDb bukuDb;

    @Override
    public BukuModel findByIdBook(long id){
        return bukuDb.findById(id).get();
    }

    @Override
    public BukuModel updateBook(BukuModel book){
        BukuModel currBook = bukuDb.findById(book.getId()).get();
        try{
            currBook.setJumlah(book.getJumlah());
            bukuDb.save(currBook);
            return currBook;
        }catch (NullPointerException e){
            return null;
        }

    }

    @Override
    public void deleteBook(BukuModel book){
        bukuDb.deleteById(book.getId());
    }

}