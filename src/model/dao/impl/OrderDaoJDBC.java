package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.OrderDao;
import model.entities.Client;
import model.entities.Login;
import model.entities.Order;

import java.sql.*;

public class OrderDaoJDBC implements OrderDao {

    private Connection conn;

    public OrderDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Order obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO ORDER_ " +
                    "(NUMBER_, TOTAL) " +
                    "VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getNumber());
            st.setDouble(2, obj.getTotal());

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
            st = conn.prepareStatement("DELETE FROM ORDER_ WHERE ORDERID_ = ?");

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
    public Order findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM ORDER_ " +
                    "WHERE ORDERID_ = ?;");

            st.setInt(1, id);

            rs = st.executeQuery();


            if (rs.next()){

                Order order = instantiateOrder(rs);

                return order;
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

    private Order instantiateOrder(ResultSet rs) throws SQLException {
        Order order = new Order(rs.getDouble("TOTAL"));

        order.setId(rs.getInt("ORDERID_"));

        order.setNumber(rs.getInt("NUMBER_"));

        return order;
    }
}
