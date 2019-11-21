package tugas.akhir.siperpus.service;

import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;

public interface UserService {
    UserModel addUser (UserModel user);
//    String generateNIP(UserDetail userDetail);

    //get user by username
    UserModel getUserByUuid(String uuid);
}
