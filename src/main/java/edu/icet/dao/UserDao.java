package edu.icet.dao;

import edu.icet.entity.User;

public interface UserDao extends CrudDao<User>{
    User authenticateUser(String email, String password);
}
