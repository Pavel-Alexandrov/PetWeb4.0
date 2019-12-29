package crud.dao;

import crud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    @Override
    public void addRole(Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        role = entityManager.merge(role);
        entityManager.persist(role);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteRole(Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        role = entityManager.merge(role);
        entityManager.remove(role);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public Role getRoleByAccess(String access) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (Role) entityManager.createQuery("FROM Role R WHERE R.access = :acs").setParameter("acs", access).getSingleResult();
    }
}