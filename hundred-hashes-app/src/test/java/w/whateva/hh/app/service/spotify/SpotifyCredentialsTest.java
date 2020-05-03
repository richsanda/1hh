package w.whateva.hh.app.service.spotify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import w.whateva.hh.app.service.spotify.SpotifyCredentials;

@SpringBootTest
public class SpotifyCredentialsTest {

    @Autowired
    private SpotifyCredentials spotifyCredentials;

    @Test
    @Disabled
    public void credentials() {

        System.out.println(spotifyCredentials.getToken());
    }
}
