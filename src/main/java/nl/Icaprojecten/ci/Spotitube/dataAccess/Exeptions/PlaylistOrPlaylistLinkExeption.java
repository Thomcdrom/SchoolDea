package nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions;

public class PlaylistOrPlaylistLinkExeption extends Exception {
    public PlaylistOrPlaylistLinkExeption() {
        super("Playlist or the link to the user couldn't be done in the database, Possible cause is that the playlist ID is already taken");
    }
}
