package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.PeminjamanBukuModel;

import java.util.List;

public interface PeminjamanBukuService {

    void addPeminjamanBuku(PeminjamanBukuModel peminjamanBuku);

    List<PeminjamanBukuModel> findAll();

    List<PeminjamanBukuModel> findAllPeminjamanBukuByUuidUser(String UuidUser);

    PeminjamanBukuModel findLoanByIdLoan(Long id);

    PeminjamanBukuModel updateStatus(PeminjamanBukuModel peminjamanBuku);

    List<PeminjamanBukuModel> getPeminjamanList();
}
