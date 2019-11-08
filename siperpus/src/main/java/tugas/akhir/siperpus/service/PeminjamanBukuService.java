package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.PeminjamanBukuModel;

import java.util.List;

public interface PeminjamanBukuService {

    void addPeminjamanBuku(PeminjamanBukuModel peminjamanBuku);

    List<PeminjamanBukuModel> findAll();

//    List<PeminjamanBukuModel> findAllPeminjamanBukuByUuidUser(String UuidUser);

    PeminjamanBukuModel updateStatus(PeminjamanBukuModel peminjamanBuku, int Status);

    List<PeminjamanBukuModel> getPeminjamanList();
}
