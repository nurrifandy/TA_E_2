package tugas.akhir.siperpus.service;

import java.util.List;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;

public interface PengadaanBukuService{
    List<PengadaanBukuModel> getListPengadaan();

    void addProcurement(PengadaanBukuModel procurement);

    //Mono<AnggotaDetailModel> getAnggotaDetail();
}