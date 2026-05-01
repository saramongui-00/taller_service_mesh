package uptc.edu.swii.login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(name = "customerid")
    private String customerid;

    @Column(name = "password")
    private String password;

    public Login(){}

    public Login(String customerid, String password) {
        this.customerid = customerid;
        this.password = password;
    }

    public String getCustomerid() { return customerid; }
    public void setCustomerid(String customerid) { this.customerid = customerid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}