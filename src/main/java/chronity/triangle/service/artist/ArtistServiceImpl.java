package chronity.triangle.service.artist;

import chronity.triangle.controller.utils.ResponseMsg;
import chronity.triangle.model.artist.Artist;
import chronity.triangle.model.user.User;
import chronity.triangle.repository.artist.ArtistRepository;
import chronity.triangle.service.user.UserService;
import chronity.triangle.validation.artist.ArtistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist getById(String artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Artist with such id: '" + artistId + "' doesn't exist."
                        )
                );
    }

    @Override
    public List<Artist> getByUserId(String userId) {
        return artistRepository.findByUserId(userId);
    }

    @Override
    public Artist add(Artist artist) {
        ArtistValidator.validate(artist, userService);
        return artistRepository.insert(initializeForInsert(artist));
    }

    @Override
    public Artist updateById(String artistId, Artist artist) {
        return artistRepository.findById(artistId)
                .map(foundArtist -> artistRepository.save(createArtistForUpdate(foundArtist, artist)))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                new ResponseMsg<>(Artist.class).internalServerError(ResponseMsg.Method.PUT))
                );
    }

    @Override
    public void deleteById(String artistId) {
        artistRepository.deleteById(artistId);
    }

    private Artist initializeForInsert(Artist artist) {
        artist.setCreationDate(LocalDateTime.now());
        artist.setLastUpdateDate(LocalDateTime.now());
        return artist;
    }

    private Artist createArtistForUpdate(Artist foundArtist, Artist requestBody) {
        ArtistValidator.validate(requestBody, userService);
        return updateArtistEntity(foundArtist, requestBody);
    }

    //TODO what to do with boolean fields - false as default
    public Artist updateArtistEntity(Artist foundArtist, Artist toUpdateArtist){
        Optional.ofNullable(toUpdateArtist.getName()).ifPresent(foundArtist::setName);
        Optional.ofNullable(toUpdateArtist.getDescription()).ifPresent(foundArtist::setDescription);
        Optional.ofNullable(toUpdateArtist.getImage()).ifPresent(foundArtist::setImage);
        Optional.ofNullable(toUpdateArtist.getGenres()).ifPresent(foundArtist::setGenres);
        Optional.ofNullable(toUpdateArtist.getLinks()).ifPresent(foundArtist::setLinks);
        Optional.ofNullable(toUpdateArtist.getUserId()).ifPresent(foundArtist::setUserId);
        Optional.ofNullable(toUpdateArtist.getCurrentProjectId()).ifPresent(foundArtist::setCurrentProjectId);
        Optional.ofNullable(toUpdateArtist.getHighlightedSongsId()).ifPresent(foundArtist::setHighlightedSongsId);
        Optional.ofNullable(toUpdateArtist.getAlbumsId()).ifPresent(foundArtist::setAlbumsId);
        Optional.ofNullable(toUpdateArtist.getProjectsId()).ifPresent(foundArtist::setProjectsId);
        Optional.ofNullable(toUpdateArtist.getSongsId()).ifPresent(foundArtist::setSongsId);
        Optional.ofNullable(toUpdateArtist.getTracksId()).ifPresent(foundArtist::setTracksId);
        Optional.ofNullable(toUpdateArtist.getWorkedWithArtistsId()).ifPresent(foundArtist::setWorkedWithArtistsId);
        return foundArtist;
    }
}
