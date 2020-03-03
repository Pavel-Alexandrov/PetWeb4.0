package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    public UserService userService;

    //логин
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String auth() {
        return "login";
    }

    //юзер
    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String UserProfile() {

        return "/user/profile";
    }

    //админ
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String userList(Model model) {
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }
}
