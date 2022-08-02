package net.maiky.banking;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Account {
    @Id
    private int id;
    private String userName;
    private String password;
    private boolean active;
    private Long activeCode;
    private double money;
    @Column(length=1000000000)
    @ElementCollection(targetClass=String.class)
    private List<String> loguri;

    private String roles;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<String> getLogs() {
        return loguri;
    }

    public void setLogs(List<String> logs) {
        this.loguri = logs;
    }

    public Long getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(Long activeCode) {
        this.activeCode = activeCode;
    }
}
