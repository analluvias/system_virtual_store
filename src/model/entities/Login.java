package model.entities;

public class Login {
    private static Integer sequence=0;
    private String user;
    private String password;
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

    @Override
    public String toString() {
        return "Login{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
