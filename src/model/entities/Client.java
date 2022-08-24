package model.entities;

import model.factories.LoginFactory;

public class Client {
    private static Integer sequence=0;

    private String name;
    private Login login;
    private String address;
    private Integer id;

    public Client(){

    }

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

    public static Integer getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }

    public Login getLogin() {
        return login;
    }

    public String getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
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
