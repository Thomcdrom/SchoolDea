package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Tracks;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.ITrackRepository;

import javax.inject.Inject;
import java.util.ArrayList;

public class TrackHelper implements ITrackHelper {

    @Inject
    ITrackRepository trackDb;

    public Tracks trackCreator(int playlistID){
        Tracks tracks = new Tracks();
        ArrayList<Track> tracklist = trackDb.getTracksFromPlaylist(playlistID).getTracks();

        tracks.setTracks(tracklist);

        return tracks;
    }
}
