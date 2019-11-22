package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;

import java.util.Optional;

public interface UserService {
    UserModel addUser (UserModel user);
    Optional<UserModel> getUserByRole (RoleModel role);
    UserModel createDummyUserPengadaanIfNotExist (RoleModel role);
//    String generateNIP(UserDetail userDetail);

    UserModel getUserByUserName(String username);
    UserModel getUserByUuid(String uuid);
    String encrypt(String password);
    //Optional <UserModel> getUserByNama(String username);
}
