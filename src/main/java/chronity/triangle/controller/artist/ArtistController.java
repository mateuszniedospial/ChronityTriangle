package chronity.triangle.controller.artist;

import chronity.triangle.config.ApiPaths;
import chronity.triangle.controller.utils.ResponseDto;
import chronity.triangle.controller.utils.json.JsonResponse;
import chronity.triangle.model.artist.Artist;
import chronity.triangle.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.Artists.ROOT)
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping(params = {ApiPaths.Artists.QueryParams.USER_ID})
    public ResponseDto<List<Artist>> getAll(
            @RequestParam(ApiPaths.Artists.QueryParams.USER_ID) Optional<String> userIdOpt
    ) {
        return userIdOpt
                .map(uId -> createGetAllResponse(artistService.getByUserId(uId)))
                .orElseGet(() -> createGetAllResponse(artistService.getAll()));
    }

    @GetMapping(value = ApiPaths.Artists.BY_ID)
    public ResponseDto<Artist> getById(@PathVariable(ApiPaths.Artists.PathParams.ID) String id) {
        return ResponseDto.create(artistService.getById(id), ServletUriComponentsBuilder.fromCurrentRequestUri());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Artist>> add(@RequestBody Artist artist) {
        var addedArtist = artistService.add(artist);
        var responseDto = createAddResponseDto(addedArtist);
        return JsonResponse.status(HttpStatus.CREATED).msg(responseDto);
    }

    @PutMapping(value = ApiPaths.Artists.BY_ID)
    public ResponseEntity<ResponseDto<Artist>> update(
            @PathVariable(ApiPaths.Artists.PathParams.ID) String id,
            @RequestBody Artist artist
    ) {
        var updatedArtist = artistService.updateById(id, artist);
        var responseDto = ResponseDto.create(updatedArtist, ServletUriComponentsBuilder.fromCurrentRequest());
        return JsonResponse.status(HttpStatus.OK).msg(responseDto);
    }

    @DeleteMapping(value = ApiPaths.Artists.BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable(ApiPaths.Artists.PathParams.ID) String id) {
        artistService.deleteById(id);
        return JsonResponse.status(HttpStatus.NO_CONTENT).noMsg();
    }

    private ResponseDto<List<Artist>> createGetAllResponse(List<Artist> artists) {
        return ResponseDto.create(artists, ServletUriComponentsBuilder.fromCurrentRequest());
    }

    private ResponseDto<Artist> createAddResponseDto(Artist artist) {
        return ResponseDto.create(
                artist,
                ServletUriComponentsBuilder.fromCurrentRequest(),
                ApiPaths.Artists.BY_ID,
                artist.getId()
        );
    }
}
