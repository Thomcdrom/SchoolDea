package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

import nl.Icaprojecten.ci.Spotitube.DTO.User;

public class UserNotUpdatedExeption extends Exception {

    public UserNotUpdatedExeption(User user) {
        super("User "+user.getUser()+" Couldn't be updated, Token not granted. Possible cause is that the user and or username isn't correct anymore");
    }
}
