package chronity.triangle.model.music.ongoing.track;

import chronity.triangle.model.Dto;
import chronity.triangle.model.music.ongoing.track.details.Category;
import chronity.triangle.model.music.shared.State;
import chronity.triangle.model.music.shared.FileFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "tracks")
public class Track implements Dto {
    @Id
    private String id;

    private String name;
    private String description;

    private Category category;
    private State state;

    List<FileFormat> fileFormatsAdded;

    //TODO recording is a file (inputStream?)
    private String MP3;
    private String FLAC;
    private String WAV;

    private List<String> artistsId;

    public Track(String name,
                 String description,
                 Category category,
                 State state,
                 List<FileFormat> fileFormatsAdded,
                 String MP3,
                 String FLAC,
                 String WAV,
                 List<String> artistsId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.state = state;
        this.fileFormatsAdded = fileFormatsAdded;
        this.MP3 = MP3;
        this.FLAC = FLAC;
        this.WAV = WAV;
        this.artistsId = artistsId;
    }
}
