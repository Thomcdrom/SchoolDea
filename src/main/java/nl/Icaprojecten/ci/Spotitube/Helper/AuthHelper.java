package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.IUserRepository;

import javax.inject.Inject;

public class AuthHelper implements IAuthHelper {

    @Inject
    private IUserRepository userRepository;

    public void CheckToken(String token) throws TokenNotFoundExeption {
        userRepository.validateUser(token);
    }

}
