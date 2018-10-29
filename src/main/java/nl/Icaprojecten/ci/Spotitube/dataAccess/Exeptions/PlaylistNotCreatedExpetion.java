package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class PlaylistNotCreatedExpetion extends Exception {
    public PlaylistNotCreatedExpetion() {
        super("Playlist couldn't be created");
    }
}
