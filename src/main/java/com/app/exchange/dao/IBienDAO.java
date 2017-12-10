package com.app.exchange.dao;

import com.app.exchange.domain.Bien;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBienDAO extends CrudRepository<Bien, Long> {
    List<Bien> findAll();
    List<Bien> findByUserId(Long id);
}
