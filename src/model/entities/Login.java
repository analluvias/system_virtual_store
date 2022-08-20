package model.entities;

import model.factories.CartFactory;

public class Login {
    private static Integer sequence=0;
    private String user;
    private String password;

    private Cart cart;
    private Integer id;

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

    public Cart getCart() {
        return cart;
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
