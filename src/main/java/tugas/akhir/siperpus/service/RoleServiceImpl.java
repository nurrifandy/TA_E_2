package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.repository.RoleDb;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDb roleDb ;

    public Optional<RoleModel> getRoleByIdRole(Long id){
        return roleDb.findById(id);
    }

    public List<RoleModel> getListRole(){
        return roleDb.findAll();
    }
}
