package w.whateva.hh.app.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import w.whateva.hh.app.HundredHashesApplication;
import w.whateva.hh.app.data.domain.Artist;
import w.whateva.hh.app.data.domain.Song;
import w.whateva.hh.app.data.repository.SongRepository;

import java.util.UUID;

@SpringBootTest(classes = HundredHashesApplication.class)
public class SongRepositoryTest {

    private final String KEY = UUID.randomUUID().toString();

    @Autowired
    private SongRepository songRepository;

    @Test
    public void basic() {
        // Artist artist = Artist.builder().name("Todd Rundgren").build();
        Song song = new Song();
        song.setKey(KEY);
        songRepository.save(song);
        Song song2 = songRepository.findByKey(KEY);

        Assertions.assertEquals(song.getId(), song2.getId());
    }
}
