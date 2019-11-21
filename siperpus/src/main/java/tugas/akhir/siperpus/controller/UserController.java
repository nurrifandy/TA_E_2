package tugas.akhir.siperpus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tugas.akhir.siperpus.model.RoleModel;
import tugas.akhir.siperpus.model.UserModel;
import tugas.akhir.siperpus.rest.StatusDetail;
import tugas.akhir.siperpus.rest.UserDetail;
import tugas.akhir.siperpus.service.RoleService;
import tugas.akhir.siperpus.service.UserRestService;
import tugas.akhir.siperpus.service.UserService;

import java.io.Console;
import java.util.ArrayList;
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
        List<RoleModel> myList = new ArrayList<>();
        for (RoleModel i : roleList){
            if(i.getNama().equals("Pustakawan") || i.getNama().equals("pustakawan")){
                if (myList.contains(i)) {
                    continue;
                } else {
                    myList.add(i);
                }
            }
        }
        UserDetail userDetail = new UserDetail();
        model.addAttribute("rolePerpus", myList);
        model.addAttribute("user", userDetail);
        return "user/form-add-user";
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserDetail user, Model model){
        RoleModel role = roleService.getRoleByIdRole(user.getRole()).get();
        UserModel newUser = new UserModel();
        newUser.setRole(role);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userService.addUser(newUser);
        userRestService.register(user,newUser).subscribe();
        return "user/add-user-submit";
    }
    
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String viewProfile( Model model){
        //UserModel user = userService.getUserByUuid(uuid);
        UserModel user = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        String uuidUser = user.getUuid();
        StatusDetail statusDetail = userRestService.getByUuid("4028e48b6e8441e2016e8446cb4f0001").block();
        
        
        // List<UserDetail> userDetail = statusDetail.getListUser();
        // //model.addAttribute("user", user);
        model.addAttribute("status", statusDetail);
        model.addAttribute("user", user);
        return "book/view-profile";
    }
}
