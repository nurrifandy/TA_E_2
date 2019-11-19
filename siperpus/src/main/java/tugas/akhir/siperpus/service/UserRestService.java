package tugas.akhir.siperpus.service;

import org.apache.catalina.User;
import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;

import java.util.List;

public interface UserRestService {
    List<UserModel> retriveListUser();
    Mono<String> register (UserDetail userDetail, UserModel userModel);
    String generateNIP(UserDetail userDetail, UserModel userModel);
}
