package com.app.exchange.dao;

import com.app.exchange.domain.Categorie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategorieDAO  extends CrudRepository<Categorie, Long> {
    List<Categorie> findAll();

}
