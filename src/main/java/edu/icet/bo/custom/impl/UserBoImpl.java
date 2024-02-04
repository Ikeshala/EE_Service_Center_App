package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.UserBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.UserDao;
import edu.icet.dao.util.DaoType;
import edu.icet.dto.UserDto;
import edu.icet.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    private UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.save(new User(
                dto.getUserId(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getType()
        ));
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDao.update(new User(
                dto.getUserId(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getType()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDao.delete(id);
    }

    @Override
    public boolean searchUser(UserDto dto) {
        return false;
    }

    @Override
    public List<UserDto> allUser() throws SQLException, ClassNotFoundException {
        List<User> entityList = userDao.getAll();
        List<UserDto> list = new ArrayList<>();
        for (User user:entityList) {
            list.add(new UserDto(
                    user.getUserId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getType()
            ));
        }
        return list;
    }
}
