package w.whateva.hh.app.service;

import w.whateva.hh.app.api.dto.Song;

import java.util.List;

public interface SongService {

    /**
     * Search songs based on a query string
     *
     * @param query
     * @return
     */
    List<Song> searchSongs(String query);

    /**
     * Search songs based on song specifications
     *
     * @param song
     * @return
     */
    List<Song> searchSongs(List<Song> song);

    /**
     * Search songs asynchronously for the purpose of side effects such as influencing caches
     *
     * @param query
     */
    void searchSongsAsync(String query);

    /**
     * Add a new song. May result in a newly generated song id.
     *
     * @param song
     * @return
     */
    Song addSong(Song song);

    /**
     * Retrieve a song.
     *
     * @param key the id for the song to retrieve
     * @return the song
     */
    Song getSong(String key);

    /**
     * Remove a song
     *
     * @param song the "specification" for the song to remove
     * @return
     */
    Song removeSong(Song song);
}
