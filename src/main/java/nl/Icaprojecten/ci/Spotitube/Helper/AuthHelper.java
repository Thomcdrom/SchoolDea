package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.UserRepository;

import javax.inject.Inject;

public class AuthHelper {

    @Inject
    UserRepository userRepository;

    public void CheckToken(String token) throws TokenNotFoundExeption {
        userRepository.validateUser(token);
    }

}
