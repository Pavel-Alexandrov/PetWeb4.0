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

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    //Юзерские страницы

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser userProfile(@PathVariable("userName") String userName) {
        User user = userService.getUserByLogin(userName);

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

    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser[] userList() {
        List<User> users = userService.getAllUsers();
        TransportUser[] transportUsers = new TransportUser[100];
        int i = 0;

        for (User user : users) {
            TransportUser transportUser = UserTranslator.userToTransportUser(user);
            transportUsers[i] = transportUser;
            i++;
        }

        return transportUsers;
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser[] deleteUser(@PathVariable("id") String id) {

        int idInt = Integer.parseInt(id);
        userService.deleteUser(idInt);

        List<User> users = userService.getAllUsers();
        TransportUser[] transportUsers = new TransportUser[100];
        int i = 0;

        for (User user : users) {
            TransportUser transportUser = UserTranslator.userToTransportUser(user);
            transportUsers[i] = transportUser;
            i++;
        }

        return transportUsers;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser[] addUser(@RequestBody TransportUser newTrUser) {
        User newUser = UserTranslator.transportUserToUserWithoutId(newTrUser);
        userService.addUser(newUser);

        List<User> users = userService.getAllUsers();
        TransportUser[] transportUsers = new TransportUser[100];
        int i = 0;

        for (User user : users) {
            TransportUser transportUser = UserTranslator.userToTransportUser(user);
            transportUsers[i] = transportUser;
            i++;
        }

        return transportUsers;
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser[] updateUser(@RequestBody TransportUser updatedUser, @PathVariable("id") String id) {
        User updUser = UserTranslator.transportUserToUser(updatedUser);
        userService.updateUser(updUser);

        List<User> users = userService.getAllUsers();
        TransportUser[] transportUsers = new TransportUser[100];
        int i = 0;

        for (User user : users) {
            TransportUser transportUser = UserTranslator.userToTransportUser(user);
            transportUsers[i] = transportUser;
            i++;
        }

        return transportUsers;
    }

    //Профиль админа
    @RequestMapping(value = "/adminProfile/{userName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TransportUser adminProfile(@PathVariable("userName") String userName) {
        User user = userService.getUserByLogin(userName);

        TransportUser transportUser = UserTranslator.userToTransportUser(user);

        return transportUser;
    }
}
