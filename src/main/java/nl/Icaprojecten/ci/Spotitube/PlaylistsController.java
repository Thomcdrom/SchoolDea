package nl.Icaprojecten.ci.Spotitube;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistsController {

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists (){
        return Response.status(201).build();
    }
}
