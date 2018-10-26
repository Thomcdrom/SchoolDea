package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class TrackNotCoupledExeption extends Exception{
    public TrackNotCoupledExeption() {
        super("Track couldn't be coupled to the playlist");
    }
}
