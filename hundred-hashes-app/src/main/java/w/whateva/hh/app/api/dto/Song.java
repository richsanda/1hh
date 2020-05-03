package w.whateva.hh.app.api.dto;

import lombok.*;

import javax.annotation.Nullable;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Song {

    private final String key;
    private final String artist;
    private final String title;
}
