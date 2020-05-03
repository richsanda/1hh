package w.whateva.hh.app.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import w.whateva.hh.app.data.domain.Song;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {

    Song findByKey(String key);
}
