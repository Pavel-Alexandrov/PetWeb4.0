package crud.dao;

import crud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    public EntityManager entityManager;

    // не используются
    @Transactional
    @Override
    public void addRole(Role role) {
        role = entityManager.merge(role);
        entityManager.persist(role);
        entityManager.flush();
    }

    // не используются
    @Transactional
    @Override
    public void deleteRole(Role role) {
        role = entityManager.merge(role);
        entityManager.remove(role);
        entityManager.flush();
    }

    @Override
    public Role getRoleByAccess(String access) {
        return (Role) entityManager.createQuery("FROM Role R WHERE R.access = :acs").setParameter("acs", access).getSingleResult();
    }
}