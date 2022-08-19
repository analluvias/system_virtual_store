package application;

import model.entities.Product;
import model.entities.ProductEspecification;
import model.entities.PurchaseItems;

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
    }

}
