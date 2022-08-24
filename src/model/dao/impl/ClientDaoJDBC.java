package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ClientDao;
import model.dao.LoginDao;
import model.entities.Client;
import model.entities.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoJDBC implements ClientDao {

    private Connection conn;

    public ClientDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Client obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO CLIENT " +
                    "(ADDRESS_, NAME_, LOGINID_) " +
                    "VALUES " +
                    "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getAddress());
            st.setString(2, obj.getName());
            st.setInt(3, obj.getLogin().getId());

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
    public void update(Client obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE CLIENT " +
                    "SET ADDRESS_ = ?, NAME_ = ? " +
                    "WHERE CLIENTID_ = ?");

            st.setString(1, obj.getAddress());
            st.setString(2, obj.getName());
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
            st = conn.prepareStatement("DELETE FROM CLIENT WHERE CLIENTID_ = ?");

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
    public Client findById(Integer id, LoginDao loginDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM CLIENT " +
                    "WHERE CLIENTID_ = ?;");

            st.setInt(1, id);

            rs = st.executeQuery();


            if (rs.next()){


                Login login = loginDao.findBYId(rs.getInt("LOGINID_"));

                Client client = instantiateClient(rs, login);

                return client;
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
    public List<Client> findAll(LoginDao loginDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT CLIENT.*, LOGIN.LOGINID_ AS id " +
                    "FROM CLIENT " +
                    "INNER JOIN LOGIN " +
                    "ON CLIENT.LOGINID_ = LOGIN.LOGINID_;");

            rs = st.executeQuery();

            List<Client> list = new ArrayList<>();
            Map<Integer, Login> map = new HashMap<>();

            //enquanto houver valores no ResultSet
            while (rs.next()){

                //vendo se já não instanciei este login nessa operação
                Login login = map.get(rs.getInt("LOGINID_"));

                if (login == null){
                    login = loginDao.findBYId(rs.getInt("LOGINID_"));

                    map.put(rs.getInt("LOGINID_"), login);
                }

                //instanciando o cliente no java
                Client client = instantiateClient(rs, login);
                list.add(client);
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

    @Override
    public Client findByLogin(Login obj, LoginDao loginDao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT CLIENT.* " +
                    "FROM CLIENT " +
                    "INNER JOIN LOGIN " +
                    "ON CLIENT.LOGINID_ = LOGIN.LOGINID_ " +
                    "WHERE LOGIN.LOGINID_ = ?;");

            st.setInt(1, obj.getId());

            rs = st.executeQuery();


            if (rs.next()){


                Login login = loginDao.findBYId(rs.getInt("LOGINID_"));

                Client client = instantiateClient(rs, login);

                return client;
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

    private static Client instantiateClient(ResultSet rs, Login login) throws SQLException {
        Client client = new Client();

        client.setId(rs.getInt("CLIENTID_"));

        client.setAddress(rs.getString("ADDRESS_"));

        client.setName(rs.getString("NAME_"));

        client.setLogin(login);

        return client;
    }
}
