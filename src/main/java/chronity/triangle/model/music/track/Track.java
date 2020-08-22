package chronity.triangle.model.music.track;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "tracks")
public class Track {
    @Id
    private String id;

    private String name;
    private String description;

    private Category category;

    private State state;

    //TODO recording is a file (inputStream?)
    private String recording;

    private List<String> artistsId;

    public Track(String name,
                 String description,
                 Category category,
                 State state,
                 List<String> artistsId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.state = state;
        this.artistsId = artistsId;
    }
}
