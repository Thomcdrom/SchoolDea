package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

import nl.Icaprojecten.ci.Spotitube.DTO.User;

public class TokenNotFoundExeption extends Exception {

    public TokenNotFoundExeption(String token) {
        super("User "+token+" Couldn't be found, access not granted. Possible cause is that the user and or username has logged in again and created a new token");
    }
}
