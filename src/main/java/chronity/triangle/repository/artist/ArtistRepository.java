package chronity.triangle.repository.artist;

import chronity.triangle.model.artist.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findByUserId(String userId);
}
