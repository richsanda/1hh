package w.whateva.hh.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import w.whateva.hh.app.api.dto.Artist;
import w.whateva.hh.app.api.dto.Song;

import java.util.List;

@RequestMapping
public interface SearchOperations {

    @GetMapping(path = {"/v1/artist/search"})
    List<Artist> searchArtists(@RequestParam String query);

    @GetMapping(path = {"/v1/song/search"})
    List<Song> searchSongs(@RequestParam String query);

    @GetMapping(path = {"/v1/song/autocomplete/{str}"})
    List<Song> completeSongs(@PathVariable(value = "str") String str);
}
