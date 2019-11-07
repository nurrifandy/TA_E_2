package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas.akhir.siperpus.model.JenisBukuModel;
import tugas.akhir.siperpus.repository.JenisBukuDb;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JenisBukuServiceImpl implements JenisBukuService {
    @Autowired
    JenisBukuDb jenisBukuDb ;

    @Override
    public List<JenisBukuModel> getJenisBukuList() {
        return jenisBukuDb.findAll();
    }

    @Override
    public Optional<JenisBukuModel> getJenisByIdJenis(Long id) {
        return jenisBukuDb.findJenisBukuModelById(id);
    }
}
