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
    public ResponseDto<List<UserDto>> getAll(){
        return ResponseDto.create(userService.getAll(), ServletUriComponentsBuilder.fromCurrentRequest());
    }

    @GetMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseDto<UserDto> getByUsername(@PathVariable("username") String username) {
        var foundUser = userService.getByUsername(username);
        return ResponseDto.create(foundUser, ServletUriComponentsBuilder.fromCurrentRequestUri());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> add(@RequestBody User user) {
        var addedUser = userService.add(user);
        var responseDto = ResponseDto.create(
                addedUser,
                ServletUriComponentsBuilder.fromCurrentRequest(),
                ApiPaths.Users.BY_USERNAME,
                addedUser.getUsername()
        );
        return JsonResponse.status(HttpStatus.CREATED).msg(responseDto);
    }

    @PutMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseEntity<ResponseDto<UserDto>> update(@PathVariable("username") String username, @RequestBody User user) {
        var updatedUser = userService.update(user);
        var responseDto = ResponseDto.create(
                updatedUser,
                ServletUriComponentsBuilder.fromCurrentRequest(),
                ApiPaths.Users.BY_USERNAME,
                updatedUser.getUsername()
        );
        return JsonResponse.status(HttpStatus.OK).msg(responseDto);
    }

    @DeleteMapping(value = ApiPaths.Users.BY_USERNAME)
    public ResponseEntity<String> deleteByUsername(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return JsonResponse.status(HttpStatus.NO_CONTENT).noMsg();
    }
}
