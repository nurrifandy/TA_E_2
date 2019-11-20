package tugas.akhir.siperpus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.AnggotaDetailModel;
import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.repository.PengadaanBukuDb;
import tugas.akhir.siperpus.rest.Setting;

@Service
@Transactional
public class PengadaanBukuServiceImpl implements PengadaanBukuService{

    @Autowired
    PengadaanBukuDb pengadaanBukuDb;

    @Override
    public List<PengadaanBukuModel> getListPengadaan(){
        return pengadaanBukuDb.findAll();
    }

    @Override
    public void addProcurement(PengadaanBukuModel procurement){
        pengadaanBukuDb.save(procurement);
    }
}