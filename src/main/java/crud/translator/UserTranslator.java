package crud.translator;

import crud.model.Role;
import crud.model.TransportUser;
import crud.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

//переводит User в TransportUser и обратно
public class UserTranslator {

    public static TransportUser userToTransportUser(User user) {

        //создаем список ролей в формате стринг для транспортного юзера
        Set<Role> roles = user.getRoles();
        Set<String> stringRoles = new HashSet<>();
        for (Role role : roles) {
            stringRoles.add(role.getAccess());
        }

        //создаем транспортного юзера
        TransportUser transportUser = new TransportUser(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                stringRoles
        );

        return transportUser;
    }

    public static User transportUserToUser(TransportUser transportUser) {

        //создается сет ролей для объекта User
        Set<Role> roles = new HashSet<>();
        Set<String> stringRoles = transportUser.getRoles();
        for (String stringRole : stringRoles) {
            Role role = new Role(stringRole);
            roles.add(role);
        }

        //перевод TransportUser в User
        User user = new User(transportUser.getUserId(), transportUser.getName(), transportUser.getLogin(), transportUser.getPassword(), roles);
        return user;
    }
}
