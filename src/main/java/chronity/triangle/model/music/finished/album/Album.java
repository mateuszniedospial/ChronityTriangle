package chronity.triangle.model.music.finished.album;

import chronity.triangle.model.Dto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "albums")
public class Album implements Dto {
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

    public Album(String name,
                 String description,
                 boolean isReleased,
                 int numberOfSongs,
                 LocalDate startDate,
                 LocalDate releaseDate,
                 String cdCover,
                 List<String> links,
                 List<String> songsId,
                 List<String> artistsId) {
        this.name = name;
        this.description = description;
        this.isReleased = isReleased;
        this.numberOfSongs = numberOfSongs;
        this.startDate = startDate;
        this.releaseDate = releaseDate;
        this.cdCover = cdCover;
        this.links = links;
        this.songsId = songsId;
        this.artistsId = artistsId;
    }
}
