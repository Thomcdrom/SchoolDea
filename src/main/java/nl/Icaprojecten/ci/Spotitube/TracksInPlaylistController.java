package nl.Icaprojecten.ci.Spotitube;

import nl.Icaprojecten.ci.Spotitube.Helper.AuthHelper;
import nl.Icaprojecten.ci.Spotitube.Helper.TrackHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.TrackDbController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists/{playlistid}/tracks")
public class TracksInPlaylistController {

    @Inject
    AuthHelper authHelper;

    @Inject
    TrackHelper helper;

    @Inject
    TrackDbController trackDb;

    @Path("/")
    @GET
    public Response getTracksFromPlaylist(
            @PathParam("playlistid") int playlistID,
            @QueryParam("token") String token){
        try{
            authHelper.CheckToken(token);

        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(200).entity(helper.trackCreator(playlistID)).build();
    }

    @Path("/{trackid}")
    @DELETE
    public Response deleteTrackFromPlaylist(
            @PathParam("playlistid") int playlistID,
            @PathParam("trackid") int trackID,
            @QueryParam("token") String token){
        try{
            authHelper.CheckToken(token);

            trackDb.deleteTrackFromPlaylist(trackID,playlistID);
        }
        catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(200).entity(helper.trackCreator(playlistID)).build();
    }
}
