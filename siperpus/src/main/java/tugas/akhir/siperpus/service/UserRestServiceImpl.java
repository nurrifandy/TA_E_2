package tugas.akhir.siperpus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.repository.UserDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
    @Autowired
    private UserDb userDb;

    @Override
    public List<UserModel> retriveListUser() {
        return userDb.findAll();
    }
}
