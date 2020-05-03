package w.whateva.hh.app.api.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Artist {

    private final String key;
    private final String name;
}
