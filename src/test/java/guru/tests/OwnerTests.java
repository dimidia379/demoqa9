package guru.tests;

import guru.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

@Tag("owner")
public class OwnerTests {
    public static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);

    @Test
    void readCredentialsTest(){
        String login = credentials.login();
        String password = credentials.password();
        String message = format("My login: %s, my password: %s", login, password);
        System.out.println(message);
    }
}
