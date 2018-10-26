package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class TrackNotRemovedExeption extends Exception {
    public TrackNotRemovedExeption() {
        super("Track couldn't be removed from the reason, Could be because it's already gone");
    }
}
