package tugas.akhir.siperpus.service;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.SuratDetailModel;

public interface SuratRestService{
    Mono<SuratDetailModel> postSurat(); 
}