package chronity.triangle.model.music.ongoing.project;

import chronity.triangle.model.Dto;
import chronity.triangle.model.music.shared.State;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "projects")
public class Project implements Dto {
    @Id
    private String id;

    private String name;
    private String description;
    private List<String> genres;
    //TODO Image file
    private String image;

    private State state;

    private LocalDate startDate;
    private LocalDate finishDate;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    private List<String> tracksId;

    private String creatorArtistId;

    public Project(String name,
                   String description,
                   List<String> genres,
                   String image,
                   State state,
                   LocalDate startDate,
                   LocalDate finishDate,
                   LocalDateTime creationDate,
                   LocalDateTime lastUpdateDate,
                   List<String> tracksId,
                   String creatorArtistId) {
        this.name = name;
        this.description = description;
        this.genres = genres;
        this.image = image;
        this.state = state;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tracksId = tracksId;
        this.creatorArtistId = creatorArtistId;
    }
}
