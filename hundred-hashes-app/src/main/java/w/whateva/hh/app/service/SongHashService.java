package w.whateva.hh.app.service;

import w.whateva.hh.app.api.dto.SongHash;
import w.whateva.hh.app.api.dto.SongHashEntry;

import java.util.List;

public interface SongHashService {

    List<SongHashEntry> setSongHashEntries(String username, List<SongHashEntry> entries);

    List<SongHashEntry> getSongHashEntries(String username);

    List<SongHash> getSongHashes();
}
