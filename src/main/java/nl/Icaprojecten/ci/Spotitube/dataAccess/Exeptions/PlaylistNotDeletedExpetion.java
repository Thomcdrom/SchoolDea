package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class PlaylistNotDeletedExpetion extends Exception {
    public PlaylistNotDeletedExpetion() {
        super("Playlist could not be deleted, possible cause. ID doesn't exist");
    }
}
