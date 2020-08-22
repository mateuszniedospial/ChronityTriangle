package chronity.triangle.repository.music.finished.album;

import chronity.triangle.model.music.finished.album.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
