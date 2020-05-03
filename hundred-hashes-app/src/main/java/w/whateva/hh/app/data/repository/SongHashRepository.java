package w.whateva.hh.app.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import w.whateva.hh.app.data.domain.SongHash;

import java.util.List;

@Repository
public interface SongHashRepository extends PagingAndSortingRepository<SongHash, Long> {

    SongHash findByKey(String key);

    @Query("SELECT sh FROM SongHash sh" +
            " LEFT JOIN sh.songHashEntries she" +
            " LEFT JOIN she.user u" +
            " WHERE u.username = :username")
    List<SongHash> userSongHashes(String username);
}
