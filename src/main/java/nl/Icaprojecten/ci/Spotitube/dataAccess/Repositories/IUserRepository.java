package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;

import nl.Icaprojecten.ci.Spotitube.DTO.User;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.UserNotUpdatedExeption;

public interface IUserRepository {

     boolean validateUser(String token) throws TokenNotFoundExeption;
     User checkLoginDetails(User user) throws UserNotUpdatedExeption;

}
