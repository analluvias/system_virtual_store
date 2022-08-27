package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.dao.ProductEspecificationDao;
import model.dao.PurchaseItemsDao;
import model.entities.Cart;
import model.entities.Product;
import model.entities.PurchaseItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseItemsDaoJDBC implements PurchaseItemsDao {

    private Connection conn;

    public PurchaseItemsDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(PurchaseItems obj, Cart cart) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO PURCHASEITEMS " +
                    "(PRODUCTID_, QUANTITY_, CARTID_) " +
                    "VALUES " +
                    "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getProduct().getId());
            st.setInt(2, obj.getQuantity());
            st.setInt(3, cart.getId());

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
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE PURCHASEITEMS " +
                    "SET PRODUCTID_ = ?, QUANTITY_ = ? " +
                    "WHERE PURCHASEITEMSID_ = ?");

            st.setInt(1, obj.getProduct().getId());
            st.setInt(2, obj.getQuantity());
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
            st = conn.prepareStatement("DELETE FROM PURCHASEITEMS WHERE PURCHASEITEMSID_ = ?");

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
    public PurchaseItems findById(Integer id, ProductDao productDao, ProductEspecificationDao productEspecificationDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT PURCHASEITEMS.*, PRODUCT.PRODUCTID_ " +
                    "FROM PURCHASEITEMS " +
                    "INNER JOIN PRODUCT " +
                    "ON PURCHASEITEMS.PRODUCTID_ = PRODUCT.PRODUCTID_ " +
                    "WHERE PURCHASEITEMS.PURCHASEITEMSID_ = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()){

                Product product = productDao.findById(rs.getInt("PRODUCTID_")
                        , productEspecificationDao);

                PurchaseItems purchaseItems = instantiatePurchaseItems(rs, product);

                return purchaseItems;
            }

            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<PurchaseItems> findAll(ProductDao productDao, ProductEspecificationDao productEspecificationDao) {
        PreparedStatement st = null; //"enviador de clausulas"
        ResultSet rs = null; // "recebedor de resultados"

        try {
            st = conn.prepareStatement("SELECT PURCHASEITEMS.*, PRODUCT.PRODUCTID_ " +
                    "FROM PURCHASEITEMS " +
                    "INNER JOIN PRODUCT " +
                    "ON PURCHASEITEMS.PRODUCTID_ = PRODUCT.PRODUCTID_");

            rs = st.executeQuery();

            List<PurchaseItems> list = new ArrayList<>();
            Map<Integer, Product> map = new HashMap<>();

            //enquanto houver valores no ResultSet
            while (rs.next()){

                Product product = map.get(rs.getInt("PRODUCTID_"));

                if (product == null){
                    product = productDao.findById(rs.getInt("PRODUCTID_"), productEspecificationDao);

                    map.put(rs.getInt("PRODUCTID_"), product);
                }

                PurchaseItems purchaseItems = instantiatePurchaseItems(rs, product);
                list.add(purchaseItems);
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

    private PurchaseItems instantiatePurchaseItems(ResultSet rs, Product product) throws SQLException {
        PurchaseItems purchaseItems = new PurchaseItems();

        purchaseItems.setId(rs.getInt("PURCHASEITEMSID_"));

        purchaseItems.setQuantity(rs.getInt("QUANTITY_"));

        purchaseItems.setProduct(product);

        return purchaseItems;
    }

}
