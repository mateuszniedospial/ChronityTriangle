package chronity.triangle.service.music.finished.song;

import chronity.triangle.repository.music.finished.song.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;
}
