package crud.controller;

import crud.model.Role;
import crud.model.TransportUser;
import crud.model.User;
import crud.service.UserService;
import crud.translator.UserTranslator;
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

    //Юзерские страницы

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser UserProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TransportUser transportUser = UserTranslator.userToTransportUser(user);

        return transportUser;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser userUpdate(@RequestBody TransportUser updatedUser) {

        User user = UserTranslator.transportUserToUser(updatedUser);
        userService.updateUser(user);

        TransportUser transportUser = UserTranslator.userToTransportUser(user);
        return transportUser;
    }

    //Админские страницы

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
