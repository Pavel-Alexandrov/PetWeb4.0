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
    public String userProfile() {

        return "/user/profile";
    }

    //админ
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String userList() {

        return "/admin/users";
    }

    //админ как юзер
    @RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
    public String adminProfile() {

        return "/admin/profile";
    }
}
