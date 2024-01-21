package edu.icet.bo.impl;

import edu.icet.bo.UserBo;
import edu.icet.dao.UserDao;
import edu.icet.entity.User;
import edu.icet.entity.UserType;

public class UserBoImpl implements UserBo {
    private final UserDao userDao;

    public UserBoImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User authenticateUser(String email, String password) {
        return null;
    }

    @Override
    public UserType getType(User user) {
        if (user != null) {
            return user.getType();
        } else {
            return null;
        }
    }
}
