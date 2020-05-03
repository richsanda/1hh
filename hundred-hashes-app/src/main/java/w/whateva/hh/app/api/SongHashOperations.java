package w.whateva.hh.app.api;

import org.springframework.web.bind.annotation.*;
import w.whateva.hh.app.api.dto.SongHash;
import w.whateva.hh.app.api.dto.SongHashEntry;

import java.util.List;

@RequestMapping
public interface SongHashOperations {

    @PostMapping("/v1/user/{username}/hash/songs")
    List<SongHashEntry> setEntries(@PathVariable(value = "username") String username, @RequestBody List<SongHashEntry> entries);

    @PatchMapping("/v1/user/{username}/hash/songs")
    List<SongHashEntry> updateEntries(@PathVariable(value = "username") String username, @RequestBody List<SongHashEntry> entries);

    @DeleteMapping("/v1/user/{username}/hash/songs")
    List<SongHashEntry> clearEntries(@PathVariable(value = "username") String username);

    @PostMapping("/v1/user/{username}/hash/song")
    SongHashEntry addEntry(@PathVariable(value = "username") String username, @RequestBody SongHashEntry entry);

    @DeleteMapping("/v1/user/{username}/hash/song")
    SongHashEntry removeEntry(@PathVariable(value = "username") String username, @RequestBody SongHashEntry entry);

    @GetMapping("/v1/hash/songs")
    List<SongHash> songHashes();
}
