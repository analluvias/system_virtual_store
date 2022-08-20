package model.entities;

import model.factories.LoginFactory;

public class Client {
    private static Integer sequence=0;

    private String name;
    private Login login;
    private String address;
    private Integer id;

    public Client(String name, String email, String password, String address) {
        this.name = name;
        this.id = sequence++;
        this.login = LoginFactory.createLogin(email, password);
        this.address = address;
    }

    public void UpdateData(String name, Login login, String address){
        if (name != null) this.name = name;
        if (login != null) this.login = login;
        if (address != null) this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", login=" + login +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
