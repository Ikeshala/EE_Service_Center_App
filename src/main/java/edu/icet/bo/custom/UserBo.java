package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.UserDto;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    boolean searchUser(UserDto dto);
    List<UserDto> allUser() throws SQLException, ClassNotFoundException;
}