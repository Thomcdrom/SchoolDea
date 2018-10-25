package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.UserDbController;

import javax.inject.Inject;

public class AuthHelper {

    @Inject
    UserDbController userDbController;

    public void CheckToken(String token) throws TokenNotFoundExeption {
        userDbController.validateUser(token);
    }

}
