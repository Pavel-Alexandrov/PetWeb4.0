package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public void addUser(User user);

    public void updateUser(User user);

    public User deleteUser(int id);

    public User getUserById(int id);

    public User getUserByLogin(String login);
}
