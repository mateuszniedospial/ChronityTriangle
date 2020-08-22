package chronity.triangle.repository.music.ongoing.project;

import chronity.triangle.model.music.ongoing.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
