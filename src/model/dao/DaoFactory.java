package model.dao;

import db.DB;
import model.dao.ClientDao;
import model.dao.LoginDao;
import model.dao.impl.*;
import model.entities.Cart;
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

    public static ProductDao createProductDao(){
        return new ProductDaoJDBC(DB.getConnection());
    }

    public static CartDao createCartDao(){
        return new CartDaoJDBC(DB.getConnection());
    }
}
