package tugas.akhir.siperpus.service;

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
    
    public Mono<SuratDetailModel> postSurat(){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("status", "okey");
        data.add("surat", "okey");
        return this.webClient.post().uri("/rest/api").syncBody(data).retrieve().bodyToMono(SuratDetailModel.class);
    }
}