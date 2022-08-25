package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.dao.ProductEspecificationDao;
import model.entities.Client;
import model.entities.Product;
import model.entities.ProductEspecification;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM PRODUCT WHERE PRODUCTID_ = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Product findById(Integer id, ProductEspecificationDao productEspecificationDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT PRODUCT.*, PRODUCTESPECIFICATION.PRODUCTESPECIFICATIONID_ " +
                    "FROM PRODUCT " +
                    "INNER JOIN PRODUCTESPECIFICATION " +
                    "ON PRODUCT.PRODUCTESPECIFICATIONID_ = PRODUCTESPECIFICATION.PRODUCTESPECIFICATIONID_ " +
                    "WHERE PRODUCT.PRODUCTID_ = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            //testando se veio algum registro
            if (rs.next()){

                //criando o departamento no java
                ProductEspecification productEspecification = productEspecificationDao
                        .findById(rs.getInt("PRODUCTESPECIFICATIONID_"));

                //instanciando o vendedor no java
                Product product = instantiateProduct(rs, productEspecification);

                return product;
            }

            //se não veio registro
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            //fechando o enviador de clausulas
            DB.closeStatement(st);

            //fechando o recebedor de resultados
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Product> findAll(ProductEspecificationDao productEspecificationDao) {


        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT PRODUCT.*, PRODUCTESPECIFICATION.PRODUCTESPECIFICATIONID_ " +
                    "FROM PRODUCT " +
                    "INNER JOIN PRODUCTESPECIFICATION " +
                    "ON PRODUCT.PRODUCTESPECIFICATIONID_ = PRODUCTESPECIFICATION.PRODUCTESPECIFICATIONID_ ");

            rs = st.executeQuery();

            List<Product> list = new ArrayList<>();
            Map<Integer, ProductEspecification> map = new HashMap<>();

            //enquanto houver valores no ResultSet
            while (rs.next()){

                ProductEspecification productEspecification = map.get(rs.getInt("PRODUCTESPECIFICATIONID_"));

                if (productEspecification == null){
                    productEspecification = productEspecificationDao.findById(rs.getInt("PRODUCTESPECIFICATIONID_"));

                    map.put(rs.getInt("PRODUCTESPECIFICATIONID_"), productEspecification);
                }

                Product product = instantiateProduct(rs, productEspecification);
                list.add(product);
            }

            return list;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

            DB.closeResultSet(rs);
        }
    }

    private Product instantiateProduct(ResultSet rs, ProductEspecification productEspecification) throws SQLException {

        Product product = new Product();

        product.setId(rs.getInt("PRODUCTID_"));

        product.setProductEspecification(productEspecification);

        return product;

    }
}
