package w.whateva.hh.app.api.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class SongHashEntry {

    private String songKey;
    private String title;
    private String artist;
    private String hash;
}
