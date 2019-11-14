package tugas.akhir.siperpus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugas.akhir.siperpus.model.PengadaanBukuModel;
import tugas.akhir.siperpus.repository.PengadaanBukuDb;

@Service
@Transactional
public class PengadaanBukuServiceImpl implements PengadaanBukuService{
    @Autowired
    PengadaanBukuDb pengadaanBukuDb;

    @Override
    public List<PengadaanBukuModel> getListPengadaan(){
        return pengadaanBukuDb.findAll();
    }
}