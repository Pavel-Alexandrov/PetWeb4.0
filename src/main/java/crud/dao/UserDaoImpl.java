package crud.dao;

import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    public EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = entityManager.createQuery("from User u").getResultList();
        return userList;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Transactional
    @Override
    public User deleteUser(int id) {
        User user = entityManager.find(User.class, id);

        if (user != null) {
            entityManager.remove(user);
            entityManager.flush();
        }

        return user;
    }

    @Override
    public User getUserById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = (User) entityManager.createQuery("FROM User U WHERE U.login = :lg").setParameter("lg", login).getSingleResult();
        return user;
    }
}