package w.whateva.hh.app.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "artist_key", unique = true)
    private String key;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "artist")
    private List<Song> songs;
}
