package com.app.exchange.service.interfaces;

import com.app.exchange.domain.Categorie;

import java.util.List;

public interface ICategorieService {
    Categorie saveCategorie(Categorie c);
    List<Categorie> getAllCategorie();
    void deleteCategorie(Long id);
    Categorie findById(Long id);


}
