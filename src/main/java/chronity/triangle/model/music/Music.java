package chronity.triangle.model.music;

import chronity.triangle.model.Dto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "music")
public class Music implements Dto {
    @Id
    private String id;

    @Indexed(name = "music_name")
    private String name;
    private String description;

    private List<String> artistsId;
    private List<String> albumsId;
    private List<String> projectsId;

    public Music(String name,
                 String description,
                 List<String> artistsId,
                 List<String> albumsId,
                 List<String> projectsId) {
        this.name = name;
        this.description = description;
        this.artistsId = artistsId;
        this.albumsId = albumsId;
        this.projectsId = projectsId;
    }
}
