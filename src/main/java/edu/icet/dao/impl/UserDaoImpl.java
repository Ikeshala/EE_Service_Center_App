package edu.icet.dao.impl;

import edu.icet.dao.UserDao;
import edu.icet.dao.util.CrudUtil;
import edu.icet.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    boolean searchUser(String id) {
        return false;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getType(),
                entity.getPassword()
        );
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User authenticateUser(String email, String password) {
        return null;
    }
}
