package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProductEspecificationDao;
import model.entities.ProductEspecification;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductEspecificationDaoJDBC implements ProductEspecificationDao {

    private Connection conn;

    public ProductEspecificationDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(ProductEspecification obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO PRODUCTESPECIFICATION " +
                    "(DESCRIPTION, PRICE) " +
                    "VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescription());
            st.setDouble(2, obj.getPrice());

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
    public void update(ProductEspecification obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE PRODUCTESPECIFICATION " +
                    "SET DESCRIPTION = ?, PRICE = ? " +
                    "WHERE PRODUCTESPECIFICATIONID_ = ?");

            st.setString(1, obj.getDescription());
            st.setDouble(2, obj.getPrice());
            st.setInt(3, obj.getId());

            st.executeUpdate();

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
            st = conn.prepareStatement("DELETE FROM PRODUCTESPECIFICATION" +
                    " WHERE PRODUCTESPECIFICATIONID_ = ?");

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
    public ProductEspecification findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT *" +
                    "FROM PRODUCTESPECIFICATION " +
                    "WHERE PRODUCTESPECIFICATIONID_ = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            //testando se veio algum registro
            if (rs.next()){

                //instanciando o vendedor no java
                ProductEspecification productEspecification =
                        instantiateProductEspecification(rs);

                return productEspecification;
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
    public List<ProductEspecification> findAll() {
        PreparedStatement st = null; //"enviador de clausulas"
        ResultSet rs = null; // "recebedor de resultados"

        try {
            st = conn.prepareStatement("SELECT *" +
                    "FROM PRODUCTESPECIFICATION;");

            rs = st.executeQuery();

            List<ProductEspecification> list = new ArrayList<>();

            //enquanto houver valores no ResultSet
            while (rs.next()){

                ProductEspecification productEspecification = new ProductEspecification();

                productEspecification = instantiateProductEspecification(rs);

                list.add(productEspecification);
            }

            return list;

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

    private ProductEspecification instantiateProductEspecification(ResultSet rs) throws SQLException {
        ProductEspecification productEspecification = new ProductEspecification();

        productEspecification.setId(rs.getInt("PRODUCTESPECIFICATIONID_"));

        productEspecification.setDescription(rs.getString("DESCRIPTION"));

        productEspecification.setPrice(rs.getDouble("PRICE"));

        return productEspecification;
    }
}
