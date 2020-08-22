package chronity.triangle.model.music.ongoing.project;

import chronity.triangle.model.Dto;
import chronity.triangle.model.music.shared.State;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
    private String genre;
    //TODO Image file
    private String image;

    private State state;

    private LocalDate startDate;
    private LocalDate finishDate;

    private List<String> tracksId;

    public Project(String name,
                   String description,
                   String genre,
                   String image,
                   State state,
                   LocalDate startDate,
                   LocalDate finishDate,
                   List<String> tracksId) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.image = image;
        this.state = state;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.tracksId = tracksId;
    }
}
