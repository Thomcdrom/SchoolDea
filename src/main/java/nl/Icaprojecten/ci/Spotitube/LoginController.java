package nl.Icaprojecten.ci.Spotitube;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.Icaprojecten.ci.Spotitube.DTO.User;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.UserNotUpdatedExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.UserRepository;

@Path("login")
    public class LoginController {

    @Inject
    private UserRepository database;

        @Path("/")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response postLogin(User user){
            try {
                User loginUser = database.checkLoginDetails(user);
                return Response.status(201).entity(Json.createObjectBuilder()
                        .add("token", loginUser.getToken().toString())
                        .add("user", loginUser.getName())
                        .build()).build();
            }
            catch (UserNotUpdatedExeption e){
                return Response.status(401).entity(e.getMessage()).build();
            }
        }
    }
