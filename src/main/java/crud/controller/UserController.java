package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String auth() {
        return "login";
    }

    //Юзерские страницы

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String UserProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);

        return "/user/profile";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String userUpdate(@RequestParam("id") String InputId, @RequestParam("login") String login,
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
    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String userList(Model model) {
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);

        return "/admin/users";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String addUser(@ModelAttribute("user") User user, @RequestParam("access") String access, Model model) {


        userService.addUser(user, access);
        model.addAttribute("userList", userService.getAllUsers());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("currUser", currUser);

        return "/admin/users";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String updateUser(@RequestParam("id") String InputId, @RequestParam("login") String login,
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
