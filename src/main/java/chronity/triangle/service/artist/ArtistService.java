package chronity.triangle.service.artist;

import chronity.triangle.model.artist.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAll();

    Artist getById(String artistId);
    List<Artist> getByUserId(String userId);

    Artist add(Artist artist);

    Artist updateById(String artistId, Artist artist);

    void deleteById(String artistId);
}
