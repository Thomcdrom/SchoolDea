package nl.Icaprojecten.ci.Spotitube.dataAccess;

import nl.Icaprojecten.ci.Spotitube.DTO.User;

public interface UserRepository {

    User getUser(String id);
}
