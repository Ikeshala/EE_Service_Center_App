package edu.icet.dao;

import edu.icet.dto.UserDto;
import edu.icet.entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {
    UserDto getUser(String email) throws SQLException, ClassNotFoundException;
}