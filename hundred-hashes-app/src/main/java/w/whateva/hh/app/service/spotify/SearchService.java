package w.whateva.hh.app.service.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import w.whateva.hh.app.api.dto.Song;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;

@Service
public class SearchService {

    private static final int MIN_POPULARITY = 10;

    private final SpotifyCredentials credentials;
    private final CacheService cacheService;

    private SpotifyApi spotifyApi;

    public SearchService(SpotifyCredentials credentials, CacheService cacheService) {
        this.credentials = credentials;
        this.cacheService = cacheService;
        initSpotifyApi();
    }

    public void initSpotifyApi() {
        spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(credentials.refreshToken())
                .build();
    }

    public List<Artist> searchArtists(String query) {

        indexArtists(query);

        try {
            final Paging<Artist> artistPaging = searchArtistsRequest(query).execute();
            return Arrays.asList(artistPaging.getItems());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Track> searchTracks(String query) {

        indexTracks(query);

        try {
            final Paging<Track> trackPaging = searchTracksRequest(query).execute();
            return Arrays.asList(trackPaging.getItems());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Song> songComplete(String str) {
        return cacheService.songComplete(str);
    }

    private void indexArtists(String query) {
        try {
            searchArtistsRequest(query).executeAsync().thenAccept(artistPaging -> {
                Arrays.stream(artistPaging.getItems()).forEach(artist -> {
                    cacheService.index(SpotifyUtility.toApi(artist));
                });
            });
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    private void indexTracks(String query) {
        try {
            searchTracksRequest(query).executeAsync().thenAccept(trackPaging -> {
                Arrays.stream(trackPaging.getItems()).forEach(track -> {
                    if (track.getPopularity() > MIN_POPULARITY) {
                        cacheService.index(SpotifyUtility.toApi(track));
                    }
                });
            });
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    private SearchArtistsRequest searchArtistsRequest(String query) {
        return spotifyApi.searchArtists(query).build();
    }

    private SearchTracksRequest searchTracksRequest(String query) {
        return spotifyApi.searchTracks(query).build();
    }
}
