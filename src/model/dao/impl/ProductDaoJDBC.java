package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.dao.ProductEspecificationDao;
import model.entities.Product;

import java.sql.*;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Product obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO PRODUCT " +
                    "(PRODUCTESPECIFICATIONID_) " +
                    "VALUES " +
                    "(?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getProductEspecification().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Erro inesperado, nenhuma linha foi adicionada.");
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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

    @Override
    public List<Product> findAll(ProductEspecificationDao productEspecificationDao) {
        return null;
    }
}
