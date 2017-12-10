package com.app.exchange.dao;


import com.app.exchange.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserDAO extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
