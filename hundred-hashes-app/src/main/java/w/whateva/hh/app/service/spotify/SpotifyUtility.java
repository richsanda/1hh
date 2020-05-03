package w.whateva.hh.app.service.spotify;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import w.whateva.hh.app.api.dto.Artist;
import w.whateva.hh.app.api.dto.Song;
import w.whateva.hh.app.api.dto.SongHash;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SpotifyUtility {

    private final static String SPOTIFY_NS = "spotify";
    private final static String SEPARATOR = ":";

    public static Artist toApi(com.wrapper.spotify.model_objects.specification.Artist input) {
        return Artist
                .builder()
                .key(id(input.getId()))
                .name(input.getName())
                .build();
    }

    public static Song toApi(com.wrapper.spotify.model_objects.specification.Track input) {
        return Song
                .builder()
                .key(id(input.getId()))
                .title(input.getName())
                .artist(Arrays.stream(input.getArtists()).map(ArtistSimplified::getName).collect(Collectors.joining(", ")))
                .build();
    }

    private static String id(String spotifyId) {
        return SPOTIFY_NS + SEPARATOR + spotifyId;
    }
}
