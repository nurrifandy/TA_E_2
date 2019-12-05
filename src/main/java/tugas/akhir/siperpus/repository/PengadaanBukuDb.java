package tugas.akhir.siperpus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.akhir.siperpus.model.PengadaanBukuModel;

@Repository
public interface PengadaanBukuDb extends JpaRepository<PengadaanBukuModel, Long>{
    
} 