package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.*;
import model.entities.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CartDaoJDBC implements CartDao {

    private Connection conn;

    public CartDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cart obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO CART " +
                    "(ORDERID_) " +
                    "VALUES " +
                    "(?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getOrder().getId());

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
            st = conn.prepareStatement("DELETE FROM CART WHERE CARTID_ = ?");

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
    public Cart findById(Integer id, OrderDao orderDao, PurchaseItemsDao purchaseItemsDao,
                         ProductDao productDao, ProductEspecificationDao productEspecificationDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT CART.*, PURCHASEITEMS.* " +
                    "FROM CART " +
                    "INNER JOIN PURCHASEITEMS " +
                    "ON CART.CARTID_ = PURCHASEITEMS.CARTID_ " +
                    "WHERE CART.CARTID_ = ?;");

            st.setInt(1, id);

            rs = st.executeQuery();

            Map<Integer, PurchaseItems> map = new HashMap<>();

            Cart cart = null;

            //enquanto houver valores no ResultSet
            while (rs.next()){

                PurchaseItems purch = map.get(rs.getInt("PURCHASEITEMSID_"));

                if (purch == null){
                    purch = purchaseItemsDao.findById(rs.getInt("PURCHASEITEMSID_"), productDao,
                            productEspecificationDao);

                    map.put(rs.getInt("PURCHASEITEMSID_"), purch);
                }

                Order order = orderDao.findById(rs.getInt("ORDERID_"));

                cart = instatiateCart(rs, map, order);
            }

            return cart;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

            DB.closeResultSet(rs);
        }
    }

    private Cart instatiateCart(ResultSet rs,Map<Integer, PurchaseItems> map, Order order) throws SQLException {
        Cart cart = new Cart();

        cart.setId(rs.getInt("CARTID_"));

        for (Map.Entry<Integer, PurchaseItems> pair : map.entrySet()){
            cart.insertPurchaseItem(pair.getValue());
        }

        cart.setOrder(order);

        return cart;
    }
}
