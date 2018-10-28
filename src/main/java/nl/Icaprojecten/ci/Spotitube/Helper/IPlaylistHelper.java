package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Playlists;
import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.IDNotEqualExeption;

public interface IPlaylistHelper  {

    void sameID(int ID, Playlist playlist) throws IDNotEqualExeption;
    Playlists playlistCreator(String token);
}
