package application;

import model.dao.DaoFactory;
import model.dao.LoginDao;
import model.dao.impl.LoginDaoJDBC;
import model.entities.*;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        ProductEspecification productEspecification1 = new ProductEspecification("Nike sneakers", 150.0);
        ProductEspecification productEspecification2 = new ProductEspecification("Macbook", 1000.0);
        ProductEspecification productEspecification3 = new ProductEspecification("Smartwatch", 200.0);

        System.out.println(productEspecification1);
        System.out.println(productEspecification2);
        System.out.println(productEspecification3);


        Product product1 = new Product(productEspecification1);
        Product product2 = new Product(productEspecification1);
        Product product3 = new Product(productEspecification2);
        Product product4 = new Product(productEspecification3);

        System.out.println();
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);


        PurchaseItems purchaseItems1 = new PurchaseItems(product1, 1);
        PurchaseItems purchaseItems2 = new PurchaseItems(product3, 1);
        PurchaseItems purchaseItems3 = new PurchaseItems(product4, 3);

        System.out.println();
        System.out.println(purchaseItems1);
        System.out.println(purchaseItems2);
        System.out.println(purchaseItems3);


        Client client1 = new Client("joao", "joao@gmail.com", "123",
                "rua das laranjeiras, 123");
        Client client2 = new Client("maria", "maria@gmail.com", "12345",
                "rua das amoreiras, 321");

        System.out.println();
        System.out.println(client1);
        client1.getLogin().generateCart();

        System.out.println();
        System.out.println(client2);
        client2.getLogin().generateCart();

        Login login1 = client1.getLogin();
        LoginDao loginDao = DaoFactory.createLoginDao();

        loginDao.insert(login1);
        System.out.println("Inserção no BD: " + login1.getId());

        Login login2 = client2.getLogin();

        loginDao.insert(login2);
        System.out.println("Inserção no BD: " + login2.getId());

        System.out.println();
        Login search = loginDao.findBYId(login1.getId());
        System.out.println("Login cliente 1: "+ search);

        System.out.println();
        List<Login> logins = loginDao.findAll();
        System.out.println();
        System.out.println("Buscar todos os logins: ");
        logins.stream().forEach((l) -> System.out.println(l));

        System.out.println();
        System.out.println("Update primeiro login e findBy id: ");
        login1.setPassword("98765");
        loginDao.update(login1);
        System.out.println(loginDao.findBYId(login1.getId()));

        System.out.println();
        System.out.println("Delete segundo login por id: (e print lista dos restantes)");
        loginDao.deleteById(login2.getId());
        logins = loginDao.findAll();
        logins.stream().forEach((l) -> System.out.println(l));

        client1.getLogin().getCart().insertPurchaseItem(purchaseItems1);
        client1.getLogin().getCart().insertPurchaseItem(purchaseItems2);
        client1.getLogin().getCart().insertPurchaseItem(purchaseItems3);
        System.out.println();
        System.out.println(client1.getLogin().getCart().toString());

        client1.getLogin().getCart().finalizePurchase();
        client1.getLogin().getCart().getOrder().createPayment();
        String invoice =  client1.getLogin().getCart().
                getOrder().getPayment().generatePayment(client1, client1.getLogin().getCart(),
                        client1.getLogin().getCart().getOrder());

        System.out.println();
        System.out.println(invoice);
    }

}
