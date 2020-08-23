package chronity.triangle.controller.user;

import chronity.triangle.config.ApiPaths;
import chronity.triangle.controller.utils.ResponseDto;
import chronity.triangle.controller.utils.json.JsonResponse;
import chronity.triangle.model.user.User;
import chronity.triangle.model.user.UserDto;
import chronity.triangle.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.Users.ROOT)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseDto<List<UserDto>> getAll() {
        return ResponseDto.create(userService.getAll(), ServletUriComponentsBuilder.fromCurrentRequest());
    }

    @GetMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseDto<UserDto> getByUsername(@PathVariable(ApiPaths.Users.PathParams.USERNAME) String username) {
        var foundUser = userService.getByUsername(username);
        return ResponseDto.create(foundUser, ServletUriComponentsBuilder.fromCurrentRequestUri());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> add(@RequestBody User user) {
        var addedUser = userService.add(user);
        var responseDto = createAddResponseDto(addedUser);
        return JsonResponse.status(HttpStatus.CREATED).msg(responseDto);
    }

    @PutMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseEntity<ResponseDto<UserDto>> update(
            @PathVariable(ApiPaths.Users.PathParams.USERNAME) String username,
            @RequestBody User user
    ) {
        var updatedUser = userService.updateByUsername(username, user);
        var responseDto = ResponseDto.create(updatedUser, ServletUriComponentsBuilder.fromCurrentRequest());
        return JsonResponse.status(HttpStatus.OK).msg(responseDto);
    }

    @DeleteMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseEntity<String> deleteByUsername(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return JsonResponse.status(HttpStatus.NO_CONTENT).noMsg();
    }

    private ResponseDto<UserDto> createAddResponseDto(UserDto userDto) {
        return ResponseDto.create(
                userDto,
                ServletUriComponentsBuilder.fromCurrentRequest(),
                ApiPaths.Users.BY_USERNAME,
                userDto.getUsername()
        );
    }
}
