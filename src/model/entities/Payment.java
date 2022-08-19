package model.entities;

public class Payment {
    private int id;
    private Double price;

    public Payment(int id, Double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
