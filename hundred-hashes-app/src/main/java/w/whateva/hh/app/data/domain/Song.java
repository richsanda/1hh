package w.whateva.hh.app.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "artist_id"})
})
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "song_key", unique = true)
    private String key;

    private String title;
    @ManyToOne
    private Artist artist;
    @OneToMany(mappedBy = "song")
    private List<SongHash> songHashes;
}
