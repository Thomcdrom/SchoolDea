package nl.Icaprojecten.ci.Spotitube;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;
import nl.Icaprojecten.ci.Spotitube.DTO.Playlists;
import nl.Icaprojecten.ci.Spotitube.Helper.PlaylistHelper;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.PlaylistNotDeletedExpetion;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.PlaylistDbController;
import nl.Icaprojecten.ci.Spotitube.dataAccess.TrackDbController;
import nl.Icaprojecten.ci.Spotitube.dataAccess.UserDbController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistsController {

   @Inject
    PlaylistDbController playlistdb;

    @Inject
    UserDbController userDb;

    @Inject
    TrackDbController trackdb;

    @Inject
    PlaylistHelper playlistHelper;

    @Path("/")
    @GET
    public Response getPlaylists (@QueryParam("token") String token){
        Playlists playlists;
        try{
            userDb.validateUser(token);

            playlists = playlistCreator(token);
        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}

        return Response.status(200).entity(playlists).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deletePlaylist (@QueryParam("token") String token, @PathParam("id") int ID){
        Playlists playlists;
        try{
            userDb.validateUser(token);
            playlistdb.deletePlaylist(ID);

            playlists = playlistCreator(token);
        }catch (TokenNotFoundExeption e){ return Response.status(403).entity("Invalid token").build();}
        catch (PlaylistNotDeletedExpetion e) {return Response.status(500).entity(e.getMessage()).build();}

        return Response.status(200).entity(playlists).build();
    }

    private Playlists playlistCreator(String token){
        Playlists playlists = new Playlists();

            ArrayList<Playlist> playlistsArray = playlistdb.getPlaylists(token);
            for(Playlist playlist:playlistsArray){
                playlist = trackdb.getTracksFromPlaylist(playlist);
            }
            playlists.setPlaylists(playlistsArray);
            playlists.setLenght(playlistHelper.calculateLenght(playlistsArray));

        return playlists;
    }
}
