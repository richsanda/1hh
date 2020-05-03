package w.whateva.hh.app.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class SongHash {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "song_hash_key")
    private String key;

    @ManyToOne
    private Song song;
    private String hash;
    @OneToMany(mappedBy = "songHash")
    private List<SongHashEntry> songHashEntries;
    private Integer count;
}
