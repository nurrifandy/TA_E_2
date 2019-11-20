package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.repository.PengadaanBukuDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService{
    @Autowired
    private PengadaanBukuDb pengadaanBukuDb;

    //ServiceProducer
    @Override
    public PengadaanBukuModel addProcurement(PengadaanBukuModel pengadaanBuku) {
        return pengadaanBukuDb.save(pengadaanBuku);
    }
}
