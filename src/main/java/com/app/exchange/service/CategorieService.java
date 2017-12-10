package com.app.exchange.service;

import com.app.exchange.dao.ICategorieDAO;
import com.app.exchange.domain.Categorie;
import com.app.exchange.service.interfaces.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements ICategorieService{

    @Autowired
    private ICategorieDAO categorieDAO;

    @Override
    public Categorie saveCategorie(Categorie c) {
        return categorieDAO.save(c);
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieDAO.findAll();
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieDAO.delete(id);
    }

    @Override
    public Categorie findById(Long id) {
        return categorieDAO.findOne(id);
    }
}
