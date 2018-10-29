package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Tracks;
import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackHelperTest {
    @Mock
     TrackRepository db = new TrackRepository();

    @InjectMocks
     TrackHelper classUnderTest = new TrackHelper();

    private Playlist playlist = new Playlist();
    private Tracks tracks = new Tracks();

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);

        Track track = new Track();
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
    public void ArrayListGetsSet(){
        Mockito.when(db.getTracksFromPlaylist(1)).thenReturn(playlist);

        Tracks tracks = classUnderTest.trackCreator(1);

        assertEquals(tracks.getTracks(), this.tracks.getTracks());
    }
}
