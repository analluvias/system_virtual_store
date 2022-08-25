package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.dao.PurchaseItemsDao;
import model.entities.PurchaseItems;

import java.sql.*;

public class PurchaseItemsDaoJDBC implements PurchaseItemsDao {

    private Connection conn;

    public PurchaseItemsDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(PurchaseItems obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO PURCHASEITEMS " +
                    "(PRODUCTID_, QUANTITY_) " +
                    "VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getProduct().getId());
            st.setInt(2, obj.getQuantity());

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
    public void update(PurchaseItems obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public PurchaseItems findById(Integer id, ProductDao productDao) {
        return null;
    }

}
