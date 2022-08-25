package model.dao;

import db.DB;
import model.dao.ClientDao;
import model.dao.LoginDao;
import model.dao.impl.ClientDaoJDBC;
import model.dao.impl.LoginDaoJDBC;
import model.dao.impl.ProductEspecificationDaoJDBC;
import model.dao.impl.PurchaseItemsDaoJDBC;
import model.entities.PurchaseItems;

public class DaoFactory {
    public static LoginDao createLoginDao(){
        return new LoginDaoJDBC(DB.getConnection());
    }

    public static ClientDao createClientDao(){
        return new ClientDaoJDBC(DB.getConnection());
    }

    public static PurchaseItemsDao createPurchaseItemsDao(){
        return new PurchaseItemsDaoJDBC(DB.getConnection());
    }

    public static ProductEspecificationDao createProductEspecificationDao(){
        return new ProductEspecificationDaoJDBC(DB.getConnection());
    }


}
