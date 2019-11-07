package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.BukuModel;

public interface BukuService{
    BukuModel findByIdBook(long id);

    BukuModel updateBook(BukuModel book);
}