package model.entities;

import model.factories.CartFactory;

public class Login {
    private static Integer sequence=0;
    private String user;
    private String password;

    private Cart cart;
    private Integer id;

    public Login() {
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
        this.id = sequence++;
    }

    public Boolean logginIn(String user, String password){
        if (this.user.equals(user) && this.password.equals(password)){
            return true;
        }
        return false;
    }

    public void generateCart(){
        this.cart = CartFactory.createCart();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
