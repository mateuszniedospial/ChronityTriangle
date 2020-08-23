package chronity.triangle.model.music.finished.song;

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
@Document(collection = "songs")
public class Song implements Dto {
    @Id
    private String id;

    private String title;
    private String description;
    private int number;
    private LocalDate startDate;
    private LocalDate releaseDate;
    private List<String> genres;

    private boolean isSingle;
    private boolean isReleased;

    private String MP3;
    private String FLAC;
    private String WAV;

    private List<String> fileFormatsAdded;

    private List<String> links;

    private String projectId;
    private List<String> albumsId;
    private List<String> artistsIds;

    public Song(String title,
                String description, int number,
                LocalDate startDate,
                LocalDate releaseDate,
                List<String> genres,
                boolean isSingle,
                boolean isReleased,
                String MP3,
                String FLAC,
                String WAV,
                List<String> fileFormatsAdded,
                List<String> links,
                String projectId,
                List<String> albumsId,
                List<String> artistsIds) {
        this.title = title;
        this.description = description;
        this.number = number;
        this.startDate = startDate;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.isSingle = isSingle;
        this.isReleased = isReleased;
        this.MP3 = MP3;
        this.FLAC = FLAC;
        this.WAV = WAV;
        this.fileFormatsAdded = fileFormatsAdded;
        this.links = links;
        this.projectId = projectId;
        this.albumsId = albumsId;
        this.artistsIds = artistsIds;
    }
}
