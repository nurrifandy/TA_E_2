package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.BukuModel;

import java.util.List;
import java.util.Optional;

public interface BukuService{
    BukuModel findByIdBook(long id);

    BukuModel updateBook(BukuModel book);

    void deleteBook(BukuModel book);

    void addBook(BukuModel book);
    List<BukuModel> getListBuku();
    Optional<BukuModel> getBukuByIdBuku(Long id);
}