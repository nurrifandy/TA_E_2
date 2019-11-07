package tugas.akhir.siperpus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.akhir.siperpus.model.BukuModel;


@Repository
public interface BukuDb extends JpaRepository<BukuModel, Long>{
    
}