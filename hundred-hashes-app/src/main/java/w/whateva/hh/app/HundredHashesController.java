package w.whateva.hh.app;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import w.whateva.hh.app.api.SearchOperations;
import w.whateva.hh.app.api.SongHashOperations;
import w.whateva.hh.app.api.dto.Artist;
import w.whateva.hh.app.api.dto.Song;
import w.whateva.hh.app.api.dto.SongHash;
import w.whateva.hh.app.api.dto.SongHashEntry;
import w.whateva.hh.app.service.SongHashService;
import w.whateva.hh.app.service.SongHashServiceImpl;
import w.whateva.hh.app.service.spotify.SearchService;
import w.whateva.hh.app.service.spotify.SpotifyUtility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class HundredHashesController implements SearchOperations, SongHashOperations {

    private final SearchService searchService;
    private final SongHashService songHashService;

    @Override
    public List<Artist> searchArtists(String query) {
        return searchService.searchArtists(query).stream().map(SpotifyUtility::toApi).collect(Collectors.toList());
    }

    @Override
    public List<Song> searchSongs(String query) {
        return searchService.searchTracks(query).stream().map(SpotifyUtility::toApi).collect(Collectors.toList());
    }

    @Override
    public List<Song> completeSongs(String str) {
        return searchService.songComplete(str);
    }

    @GetMapping(path ={"/v1/token/refresh"})
    public void refreshToken() {
        searchService.initSpotifyApi();
    }

    @Override
    public List<SongHashEntry> setEntries(String username, List<SongHashEntry> entries) {
        return songHashService.setSongHashEntries(username, entries);
    }

    @Override
    public List<SongHashEntry> updateEntries(String username, List<SongHashEntry> entries) {
        return songHashService.setSongHashEntries(username, entries);
    }

    @Override
    public List<SongHashEntry> clearEntries(String username) {
        return songHashService.setSongHashEntries(username, Collections.emptyList());
    }

    @Override
    public SongHashEntry addEntry(String username, SongHashEntry entry) {
        return null;
    }

    @Override
    public SongHashEntry removeEntry(String username, SongHashEntry entry) {
        return null;
    }

    @Override
    public List<SongHash> songHashes() {
        return songHashService.getSongHashes();
    }
}
