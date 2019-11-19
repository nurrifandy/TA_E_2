package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<RoleModel> getRoleByIdRole(Long id);
    List<RoleModel> getListRole();
}
