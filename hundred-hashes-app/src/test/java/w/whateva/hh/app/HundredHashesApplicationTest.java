package w.whateva.hh.app;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Unit test for simple App.
 */
public class HundredHashesApplicationTest {

    @Test
    public void spotify() {
        searchArtists_Sync();
    }

    private static final String accessToken = "BQCM0A0mYo3WSwpuhhBtIg2Lt2ksg8Z9Lf2LPhK2FasVrAGB-kVF1KFEcYRvLK2HJ0DLkZ-ibfShVyN60Jc";
    private static final String q = "Aguilera";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
//          .market(CountryCode.SE)
//          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
            .build();

    public static void searchArtists_Sync() {
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();

            System.out.println("Total: " + artistPaging.getTotal());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchArtists_Async() {
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = searchArtistsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Paging<Artist> artistPaging = pagingFuture.join();

            System.out.println("Total: " + artistPaging.getTotal());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }
}
