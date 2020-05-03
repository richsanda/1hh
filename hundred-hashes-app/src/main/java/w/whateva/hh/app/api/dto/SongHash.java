package w.whateva.hh.app.api.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class SongHash {

    private final String key;
    private final Song song;
    private final String hash;
    private final Integer count;
}
