package nl.Icaprojecten.ci.Spotitube;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.Helper.AuthHelper;
import nl.Icaprojecten.ci.Spotitube.Helper.PlaylistHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotDeletedExpetion;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.PlaylistDbController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistsController {

   @Inject
    PlaylistDbController playlistdb;

    @Inject
    PlaylistHelper helper;

    @Inject
    AuthHelper authHelper;

    @Path("/")
    @GET
    public Response getPlaylists (@QueryParam("token") String token){

        try{
            authHelper.CheckToken(token);

        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(200).entity(helper.playlistCreator(token)).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deletePlaylist (@QueryParam("token") String token, @PathParam("id") int ID){

        try{
            authHelper.CheckToken(token);
            playlistdb.deletePlaylist(ID, token);

        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}
        catch (PlaylistNotDeletedExpetion e) {return Response.status(500).entity(e.getMessage()).build();}

        return Response.status(200).entity(helper.playlistCreator(token)).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist){
        try{
            authHelper.CheckToken(token);

            playlistdb.createPlaylist(playlist,token);
            playlistdb.createPlaylist(playlist, token);

        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(201).entity(helper.playlistCreator(token)).build();
    }


}
