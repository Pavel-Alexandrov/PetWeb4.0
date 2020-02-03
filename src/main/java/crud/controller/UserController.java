package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String auth() {
        return "login";
    }

    //Юзерские страницы

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String UserProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);
        return "/admin/users";

//        return "/user/profile";
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String userUpdateGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/user/update";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String userUpdatePost(@RequestParam("id") String InputId, @RequestParam("login") String login,
                                 @RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        Role role = new Role("user");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Integer id = Integer.parseInt(InputId);

        User user = new User(id, login, name, password,roles);

        userService.updateUser(user);
        model.addAttribute("user", user);

        return "/user/profile";
    }

    //Админские страницы
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String userList(Model model) {
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        model.addAttribute("user", new User());

        return "/admin/add";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") User user, @RequestParam("access") String access, Model model) {


        userService.addUser(user, access);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public String updateUserGet(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/admin/update";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUserPost(@RequestParam("id") String InputId, @RequestParam("login") String login,
                                 @RequestParam("name") String name, @RequestParam("password") String password,
                                 @RequestParam("role") String inputRole, Model model) {
        Role role = new Role(inputRole);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Integer id = Integer.parseInt(InputId);

        User user = new User(id, login, name, password,roles);

        userService.updateUser(user);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);
        return "/admin/users";
    }
}
