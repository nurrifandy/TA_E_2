package tugas.akhir.siperpus.service;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;

public interface PengadaanBukuRestService {
    PengadaanBukuModel addProcurement(PengadaanBukuModel pengadaanBuku);

    Mono<AnggotaDetailModel> getAnggotaDetail(int id);
}
