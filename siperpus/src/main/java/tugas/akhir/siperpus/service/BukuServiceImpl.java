package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.repository.BukuDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class BukuServiceImpl implements BukuService {
    @Autowired
    private BukuDb bukuDb;

    @Override
    public BukuModel findByIdBook(long id) {
        return bukuDb.findById(id).get();
    }

    @Override
    public BukuModel updateBook(BukuModel book) {
        try {
            BukuModel currBook = bukuDb.findById(book.getId()).get();
            currBook.setJumlah(book.getJumlah());
            bukuDb.save(currBook);
            return currBook;
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public void deleteBook(BukuModel book) {
        bukuDb.deleteById(book.getId());
    }

    @Override
    public void addBook(BukuModel book) {
        bukuDb.save(book);
    }

    @Override
    public List<BukuModel> getListBuku() {
        return bukuDb.findAll();
    }

    @Override
    public Optional<BukuModel> getBukuByIdBuku(Long id) {
        return bukuDb.findBukuModelById(id);
    }

    @Override
    public int availableBook(BukuModel book) {
        int jumlah = 0;
        int available = 0;
        for (PeminjamanBukuModel peminjaman:book.getListPeminjaman()){
            if (peminjaman.getStatus() != 4){
                jumlah++;
            }
        }
        available = book.getJumlah()-jumlah;
        
        return available;

    }


}