package com.app.exchange.dao;

import com.app.exchange.domain.User;
import com.app.exchange.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleDAO  extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
