package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.PeminjamanBukuModel;

import java.util.List;

public class PeminjamanBukuService {

    void addPeminjamanBuku(PeminjamanBukuModel peminjamanBuku);

    List<PeminjamanBukuModel> findAll();

    List<PeminjamanBukuModel> findAllPeminjamanBukuByUuidUser(String UuidUser);

    void updateStatus(PeminjamanBukuModel peminjamanBuku, int Status);
}
