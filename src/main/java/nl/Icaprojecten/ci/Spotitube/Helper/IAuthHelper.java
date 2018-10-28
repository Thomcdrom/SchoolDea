package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;

public interface IAuthHelper {
    void CheckToken(String token) throws TokenNotFoundExeption;
}
