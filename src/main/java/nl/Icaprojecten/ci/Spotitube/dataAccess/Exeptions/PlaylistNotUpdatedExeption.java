package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class PlaylistNotUpdatedExeption extends Exception {
    public PlaylistNotUpdatedExeption() {
        super("Playlist can't be updated, possible due to a bad ID");
    }
}
