package w.whateva.hh.app.service.spotify;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

@Service
public class SpotifyCredentials {

    private static final String SPOTIFY_TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String SPOTIFY_TOKEN_REQUEST_BODY = "grant_type=client_credentials";
    private static final String CLIENT_ID = "54ceba59554c4e4dac79b338d47bca19";
    private static final String SECRET = "a975df1badfb47bda7e31f5ee2a0d9dc";

    private String token = newToken(CLIENT_ID, SECRET);

    public String getToken() {
        return token;
    }

    public String refreshToken() {
        token = newToken(CLIENT_ID, SECRET);
        return token;
    }

    private String newToken(String clientId, String secret) {
        RestTemplate restTemplate = spotifyAuthRestTemplate(clientId, secret);
        return restTemplate.postForObject(SPOTIFY_TOKEN_URL, SPOTIFY_TOKEN_REQUEST_BODY, TokenResponse.class).getAccess_token();
    }

    private static RestTemplate spotifyAuthRestTemplate(String clientId, String secret) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new BasicAuthInterceptor(clientId, secret)));
        return restTemplate;
    }

    @Getter
    @Setter
    private static final class TokenResponse {

        private String access_token;
        private String token_type;
        private Integer expires_in;
        private String scope;
    }

    @Getter
    @Builder
    private static class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

        private final String username;
        private final String password;

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {

            HttpHeaders headers = request.getHeaders();

            headers.remove(HttpHeaders.ACCEPT);
            headers.remove(HttpHeaders.CONNECTION);
            headers.remove(HttpHeaders.CONTENT_TYPE);
            headers.remove(HttpHeaders.CONTENT_LENGTH);

            String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encoding);

            return execution.execute(request, body);
        }
    }
}

