package model.dao.impl;

import model.dao.ProductDao;
import model.dao.ProductEspecificationDao;
import model.entities.Product;

import java.sql.Connection;

public class ProductDaoJDBC implements ProductDao {

    private Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Product obj) {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Product findById(Integer id, ProductEspecificationDao productEspecificationDao) {
        return null;
    }
}
