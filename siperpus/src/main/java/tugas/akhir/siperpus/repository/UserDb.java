package tugas.akhir.siperpus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.akhir.siperpus.model.BukuModel;
import tugas.akhir.siperpus.model.UserModel;


@Repository
public interface UserDb extends JpaRepository<UserModel, Long>{

    List<UserModel> findByUuid(String uuid);

    Optional<UserModel> findByRoleId(Long id);

}