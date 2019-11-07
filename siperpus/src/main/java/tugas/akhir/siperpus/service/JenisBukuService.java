package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.JenisBukuModel;

import java.util.List;
import java.util.Optional;

public interface JenisBukuService {
    List<JenisBukuModel> getJenisBukuList();
    Optional<JenisBukuModel> getJenisByIdJenis(Long id);
}
