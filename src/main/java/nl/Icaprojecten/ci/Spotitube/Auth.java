package nl.Icaprojecten.ci.Spotitube;

import java.util.UUID;

public class Auth {

    private UUID generateToken(){
        return  UUID.randomUUID();
    }

    //TODO make token generator function
    //TODO make function that updates token in DB
}
