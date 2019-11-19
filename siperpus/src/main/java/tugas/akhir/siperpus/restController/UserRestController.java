package tugas.akhir.siperpus.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;
import tugas.akhir.siperpus.service.UserRestService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @GetMapping(value = "/users")
    private List<UserModel> retriveListUser() {
        return userRestService.retriveListUser();
    }

}