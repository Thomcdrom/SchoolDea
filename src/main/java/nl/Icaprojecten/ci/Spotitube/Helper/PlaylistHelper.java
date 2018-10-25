package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;

import java.util.ArrayList;

public class PlaylistHelper {

    public int calculateLenght(ArrayList<Playlist> playlistHelpers){
        int lenght = 0;
        for(Playlist playlist : playlistHelpers){
            for(Track track: playlist.getTracks()){
                lenght += track.getLenght();
            }
        }
        return lenght;
    }
}
