package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;

import java.util.Optional;

public interface UserService {
    UserModel addUser (UserModel user);
    Optional<UserModel> getUserByRole (RoleModel role);
    UserModel createDummyUserPengadaanIfNotExist (RoleModel role);

    UserModel getUserByUserName(String username);
    UserModel getUserByUuid(String uuid);
    String encrypt(String password);
}
