package nl.Icaprojecten.ci.Spotitube;

import java.util.UUID;

public class User {
    private String user;
    private String password;
    private java.util.UUID UUID;

    //TODO make DTO for this class with Name and Token
    public User(String password, String user) {
        this.user = user;
        this.password = password;
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

    //TODO make user validate with database
    //TODO make user generate token
}
