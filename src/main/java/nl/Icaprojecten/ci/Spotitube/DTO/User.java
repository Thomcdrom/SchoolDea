package nl.Icaprojecten.ci.Spotitube.DTO;

import java.util.UUID;

public class User {
    private String user;
    private String password;
    private String name;
    private java.util.UUID UUID;

    public User(String password, String user, String name, UUID uuid) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.UUID = uuid;
    }

    public User() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//TODO make user validate with database
    //TODO make user generate token
}