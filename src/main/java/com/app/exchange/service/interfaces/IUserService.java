package com.app.exchange.service.interfaces;

import com.app.exchange.domain.User;
import com.app.exchange.domain.security.UserRole;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExists(String username, String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    User createUser(User user, Set<UserRole> lUserRoles);
    List<User> getAllUserNotEnabled();
    User findById(Long id);
}
