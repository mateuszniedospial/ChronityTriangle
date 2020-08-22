package chronity.triangle.repository.music.finished.song;

import chronity.triangle.model.music.finished.song.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
}
