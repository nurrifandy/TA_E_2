package tugas.akhir.siperpus.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.BaseResponse;
import tugas.akhir.siperpus.service.UserRestService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @GetMapping(value = "/users")
    private BaseResponse<List<UserModel>> retriveListUser() {
        BaseResponse<List<UserModel>> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(userRestService.retriveListUser());
        return response;
    }

}