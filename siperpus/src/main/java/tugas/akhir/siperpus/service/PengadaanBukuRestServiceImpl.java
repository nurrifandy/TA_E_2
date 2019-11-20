package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.repository.PengadaanBukuDb;
import tugas.akhir.siperpus.rest.Setting;

import javax.transaction.Transactional;

@Service
@Transactional
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService{
    private final WebClient webClient;

    @Autowired
    private PengadaanBukuDb pengadaanBukuDb;

    //ServiceProducer
    @Override
    public PengadaanBukuModel addProcurement(PengadaanBukuModel pengadaanBuku) {
        return pengadaanBukuDb.save(pengadaanBuku);
    }

    public PengadaanBukuRestServiceImpl(WebClient.Builder webClientBuilder){
         this.webClient = webClientBuilder.baseUrl(Setting.mockApi).build();
    }

    @Override
    public Mono<AnggotaDetailModel> getAnggotaDetail(int id){
         return this.webClient.get().uri("/rest/koperasi/anggota/" + id).retrieve().bodyToMono(AnggotaDetailModel.class);
    }
}
