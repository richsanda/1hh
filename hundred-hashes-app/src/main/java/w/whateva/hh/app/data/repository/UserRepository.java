package w.whateva.hh.app.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import w.whateva.hh.app.data.domain.Song;
import w.whateva.hh.app.data.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String key);
}
