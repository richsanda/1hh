package w.whateva.hh.app.data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;
    @OneToMany(mappedBy = "user")
    private List<SongHashEntry> songHashEntries;
}
