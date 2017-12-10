package com.app.exchange.service;

import com.app.exchange.dao.IBienDAO;
import com.app.exchange.domain.Bien;
import com.app.exchange.service.interfaces.IBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BienService implements IBienService {

    @Autowired
    private IBienDAO bienDAO;

    @Override
    public Bien saveBien(Bien bien) {
        return bienDAO.save(bien);
    }

    @Override
    public List<Bien> getAllBien() {
        return bienDAO.findAll();
    }

    @Override
    public void deleteBien(Long id) {
        bienDAO.delete(id);
    }

    @Override
    public Bien findById(Long id) {
        return bienDAO.findOne(id);
    }

    @Override
    public List<Bien> findByUser(Long id) {
        return bienDAO.findByUserId(id);
    }
}
