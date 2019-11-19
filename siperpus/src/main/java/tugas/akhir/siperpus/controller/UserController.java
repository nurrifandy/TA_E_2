package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;
import tugas.akhir.siperpus.service.RoleService;
import tugas.akhir.siperpus.service.UserRestService;
import tugas.akhir.siperpus.service.UserService;

import java.io.Console;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value="/addUser", method = RequestMethod.GET)
    private String addUserForm(Model model) {
        List<RoleModel> roleList = roleService.getListRole();
        UserDetail userDetail = new UserDetail();
        model.addAttribute("user", userDetail);
        model.addAttribute("roleList", roleList);
        return "book/form-add-user";
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserDetail user, Model model){
        RoleModel role = roleService.getRoleByIdRole(user.getRole()).get();
        UserModel newUser = new UserModel();
        newUser.setRole(role);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userService.addUser(newUser);
        userRestService.register(user,newUser);
        return "book/add-user-submit";
    }
}
