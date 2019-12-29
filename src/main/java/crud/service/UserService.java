package crud.service;

import crud.model.Role;
import crud.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void addUser(User user, String access);

    public void updateUser(User user);

    public void deleteUser(int id);

    public User getUserById(int id);

    public User getUserByLogin(String login);

    public Role getRoleByAccess(String login);
}
