package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class TrackNotFoundExeption extends Exception{
    public TrackNotFoundExeption() {
        super("Track couldn't be found in the database, Wrong ID?");
    }
}
