package tugas.akhir.siperpus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.akhir.siperpus.model.RoleModel;

import java.util.Optional;


@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long>{
    Optional<RoleModel> findById(int id);
}