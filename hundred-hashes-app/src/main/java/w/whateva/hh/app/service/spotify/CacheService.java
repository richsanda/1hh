package w.whateva.hh.app.service.spotify;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.springframework.stereotype.Service;
import w.whateva.hh.app.api.dto.Artist;
import w.whateva.hh.app.api.dto.Song;

import java.util.*;
import java.util.function.Function;

@Service
public class CacheService {

    private static final Integer INDEX_WORD_SIZE = 2;

    private Multimap<String, Artist> artistIndex = MultimapBuilder.treeKeys().hashSetValues().build();
    private Multimap<String, Song> songIndex = MultimapBuilder.treeKeys().hashSetValues().build();

    public void index(Artist artist) {
        Function<String, Void> fn = s -> {
            artistIndex.put(s, artist);
            return null;
        };
        index(artist.getName(), fn);
    }

    public void index(Song song) {
        Function<String, Void> fn = s -> {
            songIndex.put(s, song);
            return null;
        };
        index(song.getTitle(), fn);
        index(song.getArtist(), fn);
    }

    public List<Song> songComplete(String str) {
        String[] keys = index(str);
        if (keys.length == 0) return Collections.emptyList();
        Collection<Song> results = Arrays
                .stream(index(str))
                .map(fragment -> songIndex.get(fragment))
                .reduce(songIndex.get(keys[0]), (s1, s2) -> {
                    Set<Song> result = new LinkedHashSet<>(s1);
                    result.retainAll(s2);
                    return result;
                });
        return new ArrayList<>(results);
    }

    private void index(String str, Function<String, Void> function) {
        String[] indexed = index(str);
        Arrays.stream(indexed).forEach(function::apply);
    }

    String[] index(String str) {
        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");
        String[] result = new String[str.length() - INDEX_WORD_SIZE + 1];
        for (int i = 0; i < str.length() - 1; i++) {
            result[i] = str.substring(i, i + INDEX_WORD_SIZE);
        }
        return result;
    }
}