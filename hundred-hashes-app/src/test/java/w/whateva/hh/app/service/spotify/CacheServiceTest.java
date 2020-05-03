package w.whateva.hh.app.service.spotify;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import w.whateva.hh.app.api.dto.Song;
import w.whateva.hh.app.service.spotify.CacheService;

@Slf4j
@SpringBootTest(classes = CacheService.class)
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void index() {
        String[] indexed = cacheService.index("Todd Rundgren");
        log.info(String.join(", ", indexed));
    }
}
