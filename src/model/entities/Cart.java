package model.entities;

import model.entities.enums.Status;
import model.factories.OrderFactory;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Integer sequence=0;
    private List<PurchaseItems> purchaseItemsList = new ArrayList<>();
    private Integer id;
    private Status status;
    private Order order;

    public Cart() {
        this.status = Status.OPENED;
        this.id = sequence++;
    }

    public void insertPurchaseItem(PurchaseItems purchaseItems){
        purchaseItemsList.add(purchaseItems);
    }

    public void removePurchaseItem(PurchaseItems purchaseItems){
        for (PurchaseItems purchaseItem : purchaseItemsList) {
            if (purchaseItems.getId() == purchaseItem.getId()){
                purchaseItemsList.remove(purchaseItem);
            }
        }
    }

    public void updatePurchaseItemQuantity(PurchaseItems purchaseItems, Integer quantity){
        for (PurchaseItems purchaseItem : purchaseItemsList) {
            if (purchaseItems.getId() == purchaseItem.getId()){
                purchaseItem.setQuantity(quantity);
            }
        }
    }

    public Double totalCart(){
        Double total = 0.0;
        for (PurchaseItems purchaseItem : purchaseItemsList) {
            total += purchaseItem.totalPurchaseItems();
        }

        return total;
    }

    public void finalizePurchase(){
        Double total = totalCart();
        this.order = OrderFactory.createOrder(total);
    }

    public String printPurchaseItems(){
        String message = "";

        for (PurchaseItems purchaseItem : purchaseItemsList) {
            message += purchaseItem.getProduct().getProductEspecification().getDescription() + " x"+
                    purchaseItem.getQuantity() + " " +purchaseItem.totalPurchaseItems() + "\n";
        }

        return message;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "purchaseItemsList=" + purchaseItemsList.toString() +
                ", id=" + id +
                ", status=" + status +
                ", order=" + order +
                '}';
    }
}
