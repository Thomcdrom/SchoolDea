package nl.Icaprojecten.ci.Spotitube;

import nl.Icaprojecten.ci.Spotitube.DTO.Collections.Tracks;
import nl.Icaprojecten.ci.Spotitube.Helper.AuthHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.ITrackRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackController {

    @Inject
    AuthHelper authHelper;

    @Inject
    ITrackRepository trackDb;

    @Path("/")
    @GET
    public Response getAvalibleSongs(
            @QueryParam("forPlaylist") int playlistID,
            @QueryParam("token") String token){
        Tracks tracks = new Tracks();
        try{
            authHelper.CheckToken(token);

            tracks.setTracks(trackDb.getAvalibleTracks(playlistID));
        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(200).entity(tracks).build();
    }
}
