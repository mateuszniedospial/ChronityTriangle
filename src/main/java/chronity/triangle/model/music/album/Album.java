package chronity.triangle.model.music.album;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Document(collection = "albums")
public class Album {
    @Id
    private String id;

    private String name;
    private String description;
    private boolean isReleased;
    private int numberOfSongs;

    private LocalDate startDate;
    private LocalDate releaseDate;

    //TODO Image file
    private String cdCover;
    private List<String> links;

    private List<String> songsId;
    private List<String> artistsId;
}
