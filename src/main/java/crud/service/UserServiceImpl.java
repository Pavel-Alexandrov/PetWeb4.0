package crud.service;

import crud.dao.RoleDao;
import crud.dao.UserDao;
import crud.model.Role;
import crud.model.TransportUser;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Autowired
    public RoleDao roleDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {

        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {

        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.deleteUser(id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Role getRoleByAccess(String login) {
        return roleDao.getRoleByAccess(login);
    }
}
