package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJDBC;
import model.dao.impl.LoginDaoJDBC;
public class DaoFactory {
    public static LoginDao createLoginDao(){
        return new LoginDaoJDBC(DB.getConnection());
    }

    public static ClientDao createClientDao(){
        return new ClientDaoJDBC(DB.getConnection());
    }
}
