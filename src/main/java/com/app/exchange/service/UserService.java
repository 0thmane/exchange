package com.app.exchange.service;

import com.app.exchange.dao.IRoleDAO;
import com.app.exchange.dao.IUserDAO;
import com.app.exchange.domain.User;
import com.app.exchange.domain.security.UserRole;
import com.app.exchange.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }



    public User findByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }


    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(email)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernameExists(String username){
        if (findByUsername(username) != null){
            return true;
        }
        return false;
    }

    public boolean checkEmailExists(String email){
        if (findByEmail(email) != null){
            return true;
        }
        return false;
    }

    @Override
    public User createUser(User user, Set<UserRole> lUserRoles) {
        User luser = userDAO.findByUsername(user.getUsername());
        if (luser != null){
            LOG.info("Utilisateur existe deja! ");

        }else{
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            for(UserRole ur : lUserRoles){
                roleDAO.save(ur.getRole());
            }

            user.getlRoles().addAll(lUserRoles);



            luser = userDAO.save(user);
        }
        return luser;
    }

    @Override
    public List<User> getAllUserNotEnabled() {
        return userDAO.findAll().stream().filter(user -> user.isEnabled() == false).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return userDAO.findOne(id);
    }
}
