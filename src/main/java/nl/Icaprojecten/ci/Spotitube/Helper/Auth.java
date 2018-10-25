package nl.Icaprojecten.ci.Spotitube.Helper;

import java.util.UUID;

public class Auth {
    public UUID generateToken(){
        return  UUID.randomUUID();
    }

}
