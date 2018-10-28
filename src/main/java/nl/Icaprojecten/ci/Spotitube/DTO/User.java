package nl.Icaprojecten.ci.Spotitube.DTO;

import java.util.UUID;

public class User {
    private String user;
    private String password;
    private String name;
    private java.util.UUID token;

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

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private UUID generateToken(){
        return  UUID.randomUUID();
    }

}
