package nl.Icaprojecten.ci.Spotitube.Controller;

import nl.Icaprojecten.ci.Spotitube.DTO.Track;
import nl.Icaprojecten.ci.Spotitube.Helper.IAuthHelper;
import nl.Icaprojecten.ci.Spotitube.Helper.ITrackHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotCoupledExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TrackNotRemovedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.ITrackRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/{playlistid}/tracks")
public class TracksInPlaylistController {

    @Inject
    IAuthHelper authHelper;

    @Inject
    ITrackHelper helper;

    @Inject
    ITrackRepository trackDb;

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
        catch (TrackNotRemovedExeption e){return Response.status(500).entity(e.getMessage()).build();}

        return Response.status(200).entity(helper.trackCreator(playlistID)).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(
            @PathParam("playlistid") int playlistID,
            @QueryParam("token") String token,
            Track track){
        try{
            authHelper.CheckToken(token);

            trackDb.insertTrackIntoPlaylist(track,playlistID);
        }
        catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}
        catch (TrackNotFoundExeption | TrackNotCoupledExeption e) {return Response.status(500).entity(e.getMessage()).build();}

        return Response.status(200).entity(helper.trackCreator(playlistID)).build();
    }
}
