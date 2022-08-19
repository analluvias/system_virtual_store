package model.entities;

public class Product {
    private static Integer sequence=0;
    private ProductEspecification productEspecification;
    private Integer id;

    public Product(ProductEspecification productEspecification) {
        this.productEspecification = productEspecification;
        this.id = sequence++;
    }

    public ProductEspecification getProductEspecification() {
        return productEspecification;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productEspecification=" + productEspecification +
                ", id=" + id +
                '}';
    }
}
