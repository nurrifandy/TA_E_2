package tugas.akhir.siperpus.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.SuratDetailModel;
import tugas.akhir.siperpus.rest.Setting;

@Service
@Transactional
public class SuratRestServiceImpl implements SuratRestService{
    private final WebClient webClient;

    public SuratRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.situUrl).build();
   }
    
    public Mono<String> postSurat(int idJenisSurat, String keterangan, String tanggalPengajuan, String status, String noSurat, String usernameUser, String password){
        MultiValueMap<String, Object> data = new LinkedMultiValueMap();
        // data.add("idJenisSurat", idJenisSurat);
        // data.add("keterangan", keterangan);
        // data.add("tanggalPengajuan", tanggalPengajuan);
        // data.add("status", status);
        // data.add("noSurat", noSurat);
        // data.add("usernameUser", usernameUser);
        // data.add("password", password);

        data.add("idJenisSurat", 1);
        data.add("keterangan", "Test semoga bisa");
        data.add("tanggalPengajuan", "2019-10-19");
        data.add("status", "Menunggu Persetujuan");
        data.add("noSurat", "0");
        data.add("usernameUser", "mirna");
        data.add("password", "mirna1234");
        return this.webClient.post().uri("/api/v1/pengajuan-surat/add").header("Content-Type", "application/json").syncBody(data).retrieve().bodyToMono(String.class);
    }
}