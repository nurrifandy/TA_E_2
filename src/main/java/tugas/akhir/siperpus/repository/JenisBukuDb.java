package tugas.akhir.siperpus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.akhir.siperpus.model.JenisBukuModel;

import java.util.Optional;

@Repository
public interface JenisBukuDb extends JpaRepository<JenisBukuModel, Long> {
    Optional<JenisBukuModel> findJenisBukuModelById(Long id);
}
