package crud.dao;

import crud.model.Role;

public interface RoleDao {

    public void addRole(Role role);

    public void deleteRole(Role role);

    public Role getRoleByAccess(String login);
}
