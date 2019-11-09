package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;
import tugas.akhir.siperpus.repository.PeminjamanBukuDb;

import java.util.List;

@Service
@Transactional
public class PeminjamanBukuServiceImpl implements PeminjamanBukuService {

    @Autowired
    PeminjamanBukuDb peminjamanBukuDb;

    @Override
    public void addPeminjamanBuku(PeminjamanBukuModel peminjamanBuku){
        peminjamanBukuDb.save(peminjamanBuku);
    }

    @Override
    public PeminjamanBukuModel findLoanByIdLoan(Long id) { return peminjamanBukuDb.findPeminjamanBukuModelById(id).get(); }

    @Override
    public List<PeminjamanBukuModel> getPeminjamanList(){
        return peminjamanBukuDb.findAll();
    }

    @Override
    public List<PeminjamanBukuModel> findAll(){
        return peminjamanBukuDb.findAll();
    }

    public List<PeminjamanBukuModel> findAllPeminjamanBukuByUuidUser(String UuidUser){
        return peminjamanBukuDb.findPeminjamanBukuModelByUserUuid(UuidUser);
    }

    @Override
    public PeminjamanBukuModel updateStatus(PeminjamanBukuModel peminjamanBuku){
        try{
            PeminjamanBukuModel targetPeminjamanBuku = peminjamanBukuDb.findById(peminjamanBuku.getId()).get();
            targetPeminjamanBuku.setStatus(peminjamanBuku.getStatus());
            peminjamanBukuDb.save(targetPeminjamanBuku);
            return targetPeminjamanBuku;
        } catch (NullPointerException nullException) {
            return null;
        }
    }
}
