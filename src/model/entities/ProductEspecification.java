package model.entities;

public class ProductEspecification {
    private String description;
    private Double price;

    private Integer id;

    public ProductEspecification(String description, Double price, Integer id) {
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ProductEspecification{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
