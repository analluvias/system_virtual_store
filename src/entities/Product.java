package entities;

public class Product {
    private ProductEspecification productEspecification;
    private Integer id;

    public Product(ProductEspecification productEspecification, Integer id) {
        this.productEspecification = productEspecification;
        this.id = id;
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
