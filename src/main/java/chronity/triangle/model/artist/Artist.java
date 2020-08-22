package chronity.triangle.model.artist;

import chronity.triangle.model.Dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "profiles")
public class Artist implements Dto {
    @Id
    private String id;

    private String name;
    private String description;
    //TODO Image file
    private String image;
    private List<String> genres;

    private LocalDate creationDate;
    private LocalDate lastVisitDate;

    private List<URL> links;

    private boolean isOpenForCollabs;

    private String userId;
    private String currentProjectId;

    private List<String> highlightedSongsId;
    private List<String> albumsId;
    private List<String> projectsId;
    private List<String> songsId;
    private List<String> tracksId;
    private List<String> workedWithArtistsId;

    public Artist(String name,
                  String description,
                  String image,
                  List<String> genres,
                  LocalDate creationDate,
                  LocalDate lastVisitDate,
                  List<URL> links,
                  boolean isOpenForCollabs,
                  String userId,
                  String currentProjectId,
                  List<String> highlightedSongsId,
                  List<String> albumsId,
                  List<String> projectsId,
                  List<String> songsId,
                  List<String> tracksId,
                  List<String> workedWithArtistsId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.genres = genres;
        this.creationDate = creationDate;
        this.lastVisitDate = lastVisitDate;
        this.links = links;
        this.isOpenForCollabs = isOpenForCollabs;
        this.userId = userId;
        this.currentProjectId = currentProjectId;
        this.highlightedSongsId = highlightedSongsId;
        this.albumsId = albumsId;
        this.projectsId = projectsId;
        this.songsId = songsId;
        this.tracksId = tracksId;
        this.workedWithArtistsId = workedWithArtistsId;
    }
}
