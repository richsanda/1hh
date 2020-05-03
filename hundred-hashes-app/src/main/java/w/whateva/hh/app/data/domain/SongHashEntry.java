package w.whateva.hh.app.data.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class SongHashEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "song_hash_entry_key")
    private String key;

    @ManyToOne
    private User user;

    @ManyToOne
    private SongHash songHash;

    // duplicate these here to enable dissociation / asynchronous linking to SongHash
    private String songKey;
    private String title;
    private String artist;
    private String hash;
}
