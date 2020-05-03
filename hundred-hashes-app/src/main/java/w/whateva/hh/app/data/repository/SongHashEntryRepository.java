package w.whateva.hh.app.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import w.whateva.hh.app.data.domain.SongHashEntry;

@Repository
public interface SongHashEntryRepository extends PagingAndSortingRepository<SongHashEntry, Long> {

    SongHashEntry findByKey(String key);
}
