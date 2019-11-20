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
        this.webClient = webClientBuilder.baseUrl(Setting.mockApi).build();
   }
    
    public Mono<SuratDetailModel> postSurat(int idJenisSurat, String keterangan, Date tanggalPengajuan, String status, String noSurat, String usernameUser){
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("idJenisSurat", idJenisSurat);
        data.add("keterangan", keterangan);
        data.add("tanggalPengajuan", tanggalPengajuan);
        data.add("status", status);
        data.add("noSurat", noSurat);
        data.add("usernameUser", usernameUser);
        return this.webClient.post().uri("/rest/api").syncBody(data).retrieve().bodyToMono(SuratDetailModel.class);
    }
}