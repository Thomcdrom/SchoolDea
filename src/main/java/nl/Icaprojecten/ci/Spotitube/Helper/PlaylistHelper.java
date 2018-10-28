package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Playlists;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.IDNotEqualExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.PlaylistRepository;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.TrackRepository;

import javax.inject.Inject;
import java.util.ArrayList;

public class PlaylistHelper {

    @Inject
    TrackRepository trackdb;

    @Inject
    PlaylistRepository playlistdb;

    public void sameID(int ID, Playlist playlist) throws IDNotEqualExeption{
        if(ID != playlist.getId()){
            throw new IDNotEqualExeption();
        }
    }

    public Playlists playlistCreator(String token){
        Playlists playlists = new Playlists();
        ArrayList<Playlist> playlistsArray = playlistdb.getPlaylists(token);
        for(Playlist playlist:playlistsArray){
            playlist = trackdb.getTracksFromPlaylist(playlist);
        }
        playlists.setPlaylists(playlistsArray);
        playlists.setLenght(calculateLenght(playlistsArray));

        return playlists;
    }

    private int calculateLenght(ArrayList<Playlist> playlistHelpers){
        int lenght = 0;
        for(Playlist playlist : playlistHelpers){
            for(Track track: playlist.getTracks()){
                lenght += track.getLenght();
            }
        }
        return lenght;
    }
}
