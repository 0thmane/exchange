package com.app.exchange.service.interfaces;



import com.app.exchange.domain.Bien;

import java.util.List;

public interface IBienService {
    Bien saveBien(Bien bien);
    List<Bien> getAllBien();
    void deleteBien(Long id);
    Bien findById(Long id);
    List<Bien> findByUser(Long id);
}
