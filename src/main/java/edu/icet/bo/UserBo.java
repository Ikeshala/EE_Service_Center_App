package edu.icet.bo;

import edu.icet.entity.User;
import edu.icet.entity.UserType;

public interface UserBo {
    User authenticateUser(String email, String password);

    UserType getType(User user);
}
