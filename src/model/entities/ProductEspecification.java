package model.entities;

public class ProductEspecification {
    private static Integer sequence=0;
    private String description;
    private Double price;

    private Integer id;

    public ProductEspecification(){

    }

    public ProductEspecification(String description, Double price) {
        this.description = description;
        this.price = price;
        this.id = sequence++;
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

    public void setId(Integer id) {
        this.id = id;
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
