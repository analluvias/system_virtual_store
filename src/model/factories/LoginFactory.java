package model.factories;

import model.entities.Login;

import java.security.PublicKey;

public class LoginFactory {
    public static Login createLogin(String user, String password){
        return new Login(user, password);
    }
}
