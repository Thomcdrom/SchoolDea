package nl.Icaprojecten.ci.Spotitube.Helper;

import nl.Icaprojecten.ci.Spotitube.dataAccess.Exeptions.TokenNotFoundExeption;
import nl.Icaprojecten.ci.Spotitube.dataAccess.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthHelperTest {

    @Mock
    UserRepository userRepository = new UserRepository();

    @InjectMocks
    AuthHelper authHelper = new AuthHelper();

    private String token = "124234AAP";

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tokenNotValidTest() throws TokenNotFoundExeption{
        Mockito.when(userRepository.validateUser(token)).thenThrow(TokenNotFoundExeption.class);

        assertThrows(TokenNotFoundExeption.class, () ->{
            authHelper.CheckToken(token);
        });
    }
}
