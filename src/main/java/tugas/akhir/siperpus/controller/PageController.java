package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.UserDetail;
import tugas.akhir.siperpus.service.RoleService;
import tugas.akhir.siperpus.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model){
        Boolean isLogin = true;
        Boolean isRegister = false;
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("isRegister", isRegister);
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    private String registerUserForm(Model model) {
        List<RoleModel> roleList = roleService.getListRole();
        Boolean isLogin = false;
        Boolean isRegister = true;
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("isRegister", isRegister);
        model.addAttribute("listRole", roleList);
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    private String registerUserSubmit(@ModelAttribute UserModel user, Model model){
        RoleModel role = roleService.getRoleByIdRole(user.getRole().getId()).get();
        user.setRole(role);
        userService.addUser(user);
        model.addAttribute("registered", true);
        Boolean isLogin = true;
        Boolean isRegister = false;
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("isRegister", isRegister);
        return "login";
    }
}
