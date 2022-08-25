package application;

import model.dao.*;
import model.entities.*;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        ProductEspecificationDao productEspecificationDao = DaoFactory.createProductEspecificationDao();

        ProductEspecification productEspecification1 = new ProductEspecification("Nike sneakers", 150.00);
        ProductEspecification productEspecification2 = new ProductEspecification("Macbook", 1000.00);
        ProductEspecification productEspecification3 = new ProductEspecification("Smartwatch", 200.00);

        System.out.println(productEspecification1);
        System.out.println(productEspecification2);
        System.out.println(productEspecification3);

        System.out.println();
        System.out.println("---Inserting productEspecification 1, 2 and 3 into db--- ");
        productEspecificationDao.insert(productEspecification1);
        productEspecificationDao.insert(productEspecification2);
        productEspecificationDao.insert(productEspecification3);

        System.out.println();
        System.out.println("---updating productEspecification1---");
        productEspecification1.setPrice(155.00);
        productEspecificationDao.update(productEspecification1);

        /*System.out.println();
        System.out.println("---deleting productEspecification1 by id---");
        productEspecificationDao.deleteById(productEspecification1.getId());*/

        System.out.println();
        System.out.println("---Product especification find by id---");
        ProductEspecification productEspecificationFindById = productEspecificationDao.
                findById(productEspecification1.getId());
        System.out.println(productEspecificationFindById);

        System.out.println();
        System.out.println("---listing all productEspecification---");
        List<ProductEspecification> productEspecifications = productEspecificationDao.findAll();
        productEspecifications.stream().forEach((prod) -> System.out.println(prod));

        Product product1 = new Product(productEspecification1);
        Product product2 = new Product(productEspecification1);
        Product product3 = new Product(productEspecification2);
        Product product4 = new Product(productEspecification3);

        System.out.println();
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);

        ProductDao productDao = DaoFactory.createProductDao();

        System.out.println();
        System.out.println("---Inserting all products into bd---");


        PurchaseItems purchaseItems1 = new PurchaseItems(product1, 1);
        PurchaseItems purchaseItems2 = new PurchaseItems(product3, 1);
        PurchaseItems purchaseItems3 = new PurchaseItems(product4, 3);

        PurchaseItemsDao purchaseItemsDao = DaoFactory.createPurchaseItemsDao();

        System.out.println();
        System.out.println("---Inserting purchaseItems 1, 2 and 3 into db--- ");
        purchaseItemsDao.insert(purchaseItems1);
        purchaseItemsDao.insert(purchaseItems2);
        purchaseItemsDao.insert(purchaseItems3);

        System.out.println();
        System.out.println(purchaseItems1);
        System.out.println(purchaseItems2);
        System.out.println(purchaseItems3);

        ClientDao clientDao = DaoFactory.createClientDao();

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
        System.out.println("insert into BD: " + login1.getId());

        Login login2 = client2.getLogin();

        loginDao.insert(login2);
        System.out.println("insert into BD: " + login2.getId());

        System.out.println();
        Login search = loginDao.findBYId(login1.getId());
        System.out.println("Login client1: "+ search);

        System.out.println();
        List<Login> logins = loginDao.findAll();
        System.out.println();
        System.out.println("finding all logins: ");
        logins.stream().forEach((l) -> System.out.println(l));

        System.out.println();
        System.out.println("Update first login and findBy id: ");
        login1.setPassword("98765");
        loginDao.update(login1);
        System.out.println(loginDao.findBYId(login1.getId()));

        /*System.out.println();
        System.out.println("Delete segundo login por id: (e print lista dos restantes)");
        loginDao.deleteById(login2.getId());
        logins = loginDao.findAll();
        logins.stream().forEach((l) -> System.out.println(l));*/

        System.out.println();
        System.out.println("insert cliente1 into bd: ");
        clientDao.insert(client1);

        System.out.println("insert cliente2 into bd: ");
        clientDao.insert(client2);

        System.out.println();
        System.out.println("Update address client1: ");
        client1.setAddress("rua das amoreiras, 321");
        clientDao.update(client1);

        /*System.out.println();
        System.out.println("Deletando cliente 1: ");
        clientDao.deleteById(client1.getId());*/

        System.out.println();
        System.out.println("Find by id: ");
        Client searchedByidClient = clientDao.findById(client1.getId(), loginDao);
        System.out.println(searchedByidClient);

        System.out.println();
        System.out.println("Find all: ");
        List<Client> clients = clientDao.findAll(loginDao);
        clients.stream().forEach((client_) -> System.out.println(client_));

        System.out.println();
        System.out.println("Find client by login id: ");
        Client clientByLoginId = clientDao.findByLogin(login1, loginDao);
        System.out.println(clientByLoginId);

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
