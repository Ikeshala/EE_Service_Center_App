package edu.icet.dao.impl;

import edu.icet.dao.UserDao;
import edu.icet.dao.util.HibernateUtil;
import edu.icet.dto.UserDto;
import edu.icet.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
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
    public UserDto getUser(String email) {
        try (Session session = HibernateUtil.getSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);

            User user = query.uniqueResult();

            if (user != null) {
                return new UserDto(
                        user.getEmail(),
                        user.getPassword(),
                        user.getType()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}