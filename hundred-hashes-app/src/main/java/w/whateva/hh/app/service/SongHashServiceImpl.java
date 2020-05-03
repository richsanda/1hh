package w.whateva.hh.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import w.whateva.hh.app.data.domain.*;
import w.whateva.hh.app.data.repository.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SongHashServiceImpl implements SongHashService {

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final SongHashRepository songHashRepository;
    private final SongHashEntryRepository songHashEntryRepository;

    @Override
    public List<w.whateva.hh.app.api.dto.SongHashEntry> getSongHashEntries(String username) {
        User user = findUser(username);
        if (null == user) return null;
        return userSongHashEntries(user);
    }

    @Override
    public java.util.List<w.whateva.hh.app.api.dto.SongHashEntry> setSongHashEntries(String username, List<w.whateva.hh.app.api.dto.SongHashEntry> entries) {
        User user = findUser(username);
        user.setSongHashEntries(entries.stream().map(this::toDomain).collect(Collectors.toList()));
        songHashEntryRepository.saveAll(user.getSongHashEntries());
        userRepository.save(user);
        return userSongHashEntries(user);
    }

    public List<w.whateva.hh.app.api.dto.SongHash> getSongHashes() {

        Pageable paging = PageRequest.of(0, 20, Sort.by("count"));
        Page<SongHash> page = songHashRepository.findAll(paging);
        return page.get().map(SongHashServiceImpl::toApi).collect(Collectors.toList());
    }

    private List<w.whateva.hh.app.api.dto.SongHashEntry> userSongHashEntries(User user) {
        return user.getSongHashEntries()
                .stream()
                .map(SongHashServiceImpl::toApi)
                .collect(Collectors.toList());
    }

    private SongHashEntry toDomain(w.whateva.hh.app.api.dto.SongHashEntry api) {

        SongHashEntry result = new SongHashEntry();
        result.setSongKey(api.getSongKey());
        result.setArtist(api.getArtist());
        result.setTitle(api.getTitle());
        result.setHash(api.getHash());
        return result;
    }

    private SongHash findOrCreateSongHash(w.whateva.hh.app.api.dto.SongHash in) {
        if (null == in) return null;
        return findOrCreateSongHash(in.getKey());
    }

    private SongHash findOrCreateSongHash(String key) {
        SongHash result;
        if (null != key) {
            result = songHashRepository.findByKey(key);
        } else {
            result = new SongHash();
            result.setKey(newSongHashKey());
            songHashRepository.save(result);
        }
        return result;
    }

    private static String newSongHashKey() {
        return UUID.randomUUID().toString();
    }

    private Song findOrCreateSong(w.whateva.hh.app.api.dto.SongHash in) {
        if (null == in) return null;
        return findOrCreateSong(in.getSong());
    }

    private Song findOrCreateSong(w.whateva.hh.app.api.dto.Song in) {
        if (null == in) return null;
        return findOrCreateSong(in.getKey());
    }

    private Song findOrCreateSong(String key) {
        Song result;
        if (null != key) {
            result = songRepository.findByKey(key);
        } else {
            result = new Song();
            result.setKey(newSongKey());
            songRepository.save(result);
        }
        return result;
    }

    private static String newSongKey() {
        return UUID.randomUUID().toString();
    }

    private Artist findOrCreateArtist(w.whateva.hh.app.api.dto.SongHash in) {
        if (null == in) return null;
        return findOrCreateArtist(in.getSong());
    }

    private Artist findOrCreateArtist(w.whateva.hh.app.api.dto.Song in) {
        if (null == in) return null;
        return findOrCreateArtist(in.getKey());
    }

    private Artist findOrCreateArtist(String key) {
        Artist result;
        if (null != key) {
            result = artistRepository.findByKey(key);
        } else {
            result = new Artist();
            result.setKey(newArtistKey());
            artistRepository.save(result);
        }
        return result;
    }

    private static String newArtistKey() {
        return UUID.randomUUID().toString();
    }

    private User findUser(String username) {
        User user = userRepository.findByUsername(username);
        return null != user ? user : createUser(username); //TODO: undo this, make user creation explicit
    }

    private User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    private static w.whateva.hh.app.api.dto.SongHashEntry toApi(SongHashEntry domain) {
        return w.whateva.hh.app.api.dto.SongHashEntry
                .builder()
                .songKey(domain.getSongKey())
                .title(domain.getTitle())
                .artist(domain.getArtist())
                .hash(domain.getHash())
                .build();
    }

    private static w.whateva.hh.app.api.dto.SongHash toApi(SongHash domain) {
        return w.whateva.hh.app.api.dto.SongHash
                .builder()
                .key(domain.getKey())
                .song(toApi(domain.getSong()))
                .hash(domain.getHash())
                .count(domain.getCount())
                .build();
    }

    private static w.whateva.hh.app.api.dto.Song toApi(Song domain) {
        return w.whateva.hh.app.api.dto.Song
                .builder()
                .key(domain.getKey())
                .title(domain.getTitle())
                .artist(domain.getArtist().getName())
                .build();
    }
}