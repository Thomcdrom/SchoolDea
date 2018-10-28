package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;


import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotDeletedExpetion;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotUpdatedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotCoupledExeption;

import java.util.ArrayList;

public interface IPlaylistRepository {

    ArrayList<Playlist> getPlaylists(String token);
    void deletePlaylist( int playlistID, String token) throws PlaylistNotDeletedExpetion;
    void createPlaylist(Playlist playlist, String token) throws PlaylistNotDeletedExpetion, TrackNotCoupledExeption;
    void updatePlaylist(Playlist playlist, String token) throws PlaylistNotUpdatedExeption, TrackNotCoupledExeption;

}
