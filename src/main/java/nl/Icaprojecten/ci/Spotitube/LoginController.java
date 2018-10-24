package nl.Icaprojecten.ci.Spotitube;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.Icaprojecten.ci.Spotitube.User;

@Path("login")
    public class LoginController {

        @Path("/")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public User postLogin(User user){
            //TODO make it return UserDTO
            return user;
        }
    }
