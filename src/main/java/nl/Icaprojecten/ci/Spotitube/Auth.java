package nl.Icaprojecten.ci.Spotitube;

import java.util.UUID;

public class Auth {
    public UUID generateToken(){
            System.out.println(UUID.randomUUID());
        return  UUID.randomUUID();
    }

}
