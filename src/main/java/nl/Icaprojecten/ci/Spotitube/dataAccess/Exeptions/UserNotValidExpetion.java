package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class UserNotValidExpetion extends Exception{
    public UserNotValidExpetion() {
        super("username of password isn't valid");
    }
}
