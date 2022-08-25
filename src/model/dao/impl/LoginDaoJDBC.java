package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.LoginDao;
import model.entities.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoJDBC implements LoginDao {

    private Connection conn;

    public LoginDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Login obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO LOGIN " +
                    "(USER_, PASSWORD_, CARTID_) " +
                    "VALUES " +
                    "(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getUser());
            st.setString(2, obj.getPassword());
            st.setInt(3, obj.getCart().getId());

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
    public void update(Login obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE LOGIN " +
                    "SET USER_ = ?, PASSWORD_ = ?, CARTID_ = ? " +
                    "WHERE LOGINID_ = ?");

            st.setString(1, obj.getUser());
            st.setString(2, obj.getPassword());
            st.setInt(3, obj.getCart().getId());
            st.setInt(4, obj.getId());

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
            st = conn.prepareStatement("DELETE FROM LOGIN WHERE LOGINID_ = ?;");

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
    public Login findBYId(Integer id) {
        PreparedStatement st = null; //"enviador de clausulas"
        ResultSet rs = null; // "recebedor de resultados"

        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM LOGIN " +
                    "WHERE LOGINID_ = ?;");

            st.setInt(1, id);

            rs = st.executeQuery();

            //testando se veio algum registro
            if (rs.next()){

                //instanciando o vendedor no java
                Login login = instantiateLogin(rs);

                return login;
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
    public List<Login> findAll() {


        PreparedStatement st = null; //"enviador de clausulas"
        ResultSet rs = null; // "recebedor de resultados"

        try {
            st = conn.prepareStatement("SELECT *" +
                    "FROM LOGIN; ");

            rs = st.executeQuery();

            List<Login> list = new ArrayList<>();

            //enquanto houver valores no ResultSet
            while (rs.next()){
                //instanciando o Login no java
                Login login = instantiateLogin(rs);
                list.add(login);
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

    protected Login instantiateLogin(ResultSet rs) throws SQLException {
        Login login = new Login();

        login.setId(rs.getInt("LOGINID_"));

        login.setUser(rs.getString("USER_"));

        login.setPassword(rs.getString("PASSWORD_"));

        return login;
    }
}
