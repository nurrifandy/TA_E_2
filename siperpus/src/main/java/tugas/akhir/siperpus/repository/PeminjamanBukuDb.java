package tugas.akhir.siperpus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.akhir.siperpus.model.PeminjamanBukuModel;

import java.util.List;

@Repository
public class PeminjamanBukuDb extends JpaRepository<PeminjamanBukuModel, Long> {
    List<PeminjamanBukuModel> findByUuidUser(String UuidUser);
}
