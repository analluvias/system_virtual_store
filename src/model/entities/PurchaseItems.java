package model.entities;

public class PurchaseItems {
    private static Integer sequence=0;
    private Integer id;
    private Product product;
    private Integer quantity;

    public PurchaseItems(){

    }

    public PurchaseItems(Product product, Integer quantity) {
        this.id = sequence++;
        this.product = product;
        this.quantity = quantity;
    }

    public PurchaseItems(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double totalPurchaseItems(){
        return this.getQuantity() * this.getProduct().getProductEspecification().getPrice();
    }

    @Override
    public String toString() {
        return "PurchaseItems{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
