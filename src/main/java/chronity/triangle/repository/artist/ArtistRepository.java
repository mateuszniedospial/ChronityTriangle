package chronity.triangle.repository.artist;

import chronity.triangle.model.artist.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String> {
}
