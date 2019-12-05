package tugas.akhir.siperpus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeminjamanBukuDb extends JpaRepository<PeminjamanBukuModel, Long> {
    Optional<PeminjamanBukuModel> findPeminjamanBukuModelById(Long id);
    List<PeminjamanBukuModel> findPeminjamanBukuModelByUserUuid(String UuidUser);
}
