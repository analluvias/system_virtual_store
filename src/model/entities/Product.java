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

    public void setProductEspecification(ProductEspecification productEspecification) {
        this.productEspecification = productEspecification;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productEspecification=" + productEspecification +
                ", id=" + id +
                '}';
    }
}
