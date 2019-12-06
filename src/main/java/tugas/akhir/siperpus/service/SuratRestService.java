package tugas.akhir.siperpus.service;

import java.util.Date;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.SuratDetailModel;

public interface SuratRestService{
    Mono<String> postSurat(int idJenisSurat, String keterangan, Date tanggalPengajuan, String status, String noSurat, String usernameUser, String password); 
}