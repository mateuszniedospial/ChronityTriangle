package chronity.triangle.validation.artist;

import chronity.triangle.model.artist.Artist;
import chronity.triangle.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class ArtistValidator {
    private ArtistValidator() {}

    public static void validate(Artist requestBody, UserService userService) {
        Optional.ofNullable(requestBody.getUserId()).ifPresent((userId) -> validateUserId(userId, userService));
    }

    private static void validateUserId(String userId, UserService userService) {
        Optional.ofNullable(userId)
                .ifPresentOrElse(
                        uid -> throwIfUserByIdDoesntExist(uid, userService),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "User id has not been provided in request body.");
                        }
                );
    }

    private static void throwIfUserByIdDoesntExist(String id, UserService userService){
        if(! userService.existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User of id: '" + id + "' doesn't exist.");
    }
}
