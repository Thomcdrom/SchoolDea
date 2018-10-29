package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Playlists;
import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Tracks;
import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.IDNotEqualExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.PlaylistRepository;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaylistHelperTest {

    @Mock
    PlaylistRepository playlistRepository = new PlaylistRepository();

    @Mock
    TrackRepository trackRepository = new TrackRepository();

    @InjectMocks
    PlaylistHelper classUnderTest = new PlaylistHelper();

    private Playlist playlist = new Playlist();
    private Tracks tracks = new Tracks();
    private Track track = new Track();

    private String token = "124234AAP";

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);

        track = new Track();
        track.setIdTrack(1);
        track.setTitle("test");
        track.setLenght(2);

        ArrayList<Track> trackArrayList = new ArrayList<Track>();
        trackArrayList.add(track);
        trackArrayList.add(track);

        playlist.setId(1);
        playlist.setOwner(true);
        playlist.setName("apple pop");
        playlist.setTracks(trackArrayList);

        tracks.setTracks(playlist.getTracks());
    }

    @Test
    public void notSameID(){
        assertThrows(IDNotEqualExeption.class, () -> {
            classUnderTest.sameID(2,playlist);
        });
    }

    @Test
    public void createsPlaylists(){
        ArrayList<Playlist> playlistArrayList = new ArrayList<>();
        playlistArrayList.add(this.playlist);
        playlistArrayList.add(this.playlist);

        Mockito.when(playlistRepository.getPlaylists(token)).thenReturn(playlistArrayList);
        Mockito.when(trackRepository.getTracksFromPlaylist(playlist)).thenReturn(playlist);

        Playlists playlistTest = classUnderTest.playlistCreator(token);

        assertEquals(playlistTest.getPlaylists(),playlistArrayList);
    }
}
