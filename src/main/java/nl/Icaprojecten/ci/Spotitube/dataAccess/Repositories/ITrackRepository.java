package nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotCoupledExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotRemovedExeption;

import java.util.ArrayList;

public interface ITrackRepository {
    Playlist getTracksFromPlaylist(int playlistID);
    Playlist getTracksFromPlaylist(Playlist playlist);
    ArrayList<Track> getAvalibleTracks(int playlistID);
    void deleteTrackFromPlaylist(int trackID, int playlistID) throws TrackNotRemovedExeption;
    void insertTrackIntoPlaylist(Track track, int playlistID) throws TrackNotFoundExeption, TrackNotCoupledExeption;
}
