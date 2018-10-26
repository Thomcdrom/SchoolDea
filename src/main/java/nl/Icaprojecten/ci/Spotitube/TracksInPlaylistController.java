package nl.Icaprojecten.ci.Spotitube;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Tracks;
import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.Helper.AuthHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.TrackDbController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/playlists/{playlistid}/tracks")
public class TracksInPlaylistController {

    @Inject
    AuthHelper authHelper;

    @Inject
    TrackDbController trackDb;

    @Path("/tracks")
    @GET
    public Response getTracksFromPlaylist(@PathParam("playlistid") int playlistID, @QueryParam("token") String token){
        Tracks tracks = new Tracks();
        try{
            authHelper.CheckToken(token);

            ArrayList<Track> tracklist = trackDb.getTracksFromPlaylist(playlistID).getTracks();

            tracks.setTracks(tracklist);
        }catch (TokenNotFoundExeption e){ return javax.ws.rs.core.Response.status(403).entity("Invalid token").build();}

        return javax.ws.rs.core.Response.status(200).entity(tracks).build();
    }
    }
}
