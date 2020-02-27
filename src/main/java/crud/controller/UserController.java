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
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

//    Убран в PageController
//    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public String auth() {
//        return "login";
//    }

    //Юзерские страницы

//    Убран в PageController
//    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public String UserProfile(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);
//        model.addAttribute("userList", userService.getAllUsers());
//        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        model.addAttribute("currUser", currUser);
//
//        return "/user/profile";
//    }

    @RequestMapping(value = "/user", method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User userUpdate(@RequestBody User updatedUser) {
        userService.updateUser(updatedUser);

        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currUser;
    }

    //Админские страницы

//    Убран в PageController
//    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public String userList(Model model) {
//        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        model.addAttribute("currUser", currUser);
//        model.addAttribute("userList", userService.getAllUsers());
//
//        return "/admin/users";
//    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        List<User> userList = userService.getAllUsers();

        return userList;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
        List<User> userList = userService.getAllUsers();

        return userList;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> updateUser(@RequestBody User updatedUser) {
        userService.updateUser(updatedUser);
        List<User> userList = userService.getAllUsers();

        return userList;
    }
}
