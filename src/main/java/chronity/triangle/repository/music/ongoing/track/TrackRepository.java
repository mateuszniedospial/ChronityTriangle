package chronity.triangle.repository.music.ongoing.track;

import chronity.triangle.model.music.ongoing.track.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track, String> {
}
