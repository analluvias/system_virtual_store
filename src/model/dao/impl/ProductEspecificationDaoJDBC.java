package model.dao.impl;

import model.dao.ProductEspecificationDao;
import model.entities.ProductEspecification;

import java.sql.Connection;

public class ProductEspecificationDaoJDBC implements ProductEspecificationDao {

    private Connection conn;

    public ProductEspecificationDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(ProductEspecification obj) {

    }

    @Override
    public void update(ProductEspecification obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public ProductEspecification findById(Integer id) {
        return null;
    }
}
