package chronity.triangle.service.music.ongoing.track;

import chronity.triangle.repository.music.ongoing.track.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private TrackRepository trackRepository;
}
