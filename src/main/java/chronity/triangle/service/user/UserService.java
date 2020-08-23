package chronity.triangle.service.user;

import chronity.triangle.model.user.User;
import chronity.triangle.model.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getByUsername(String username);
    UserDto getByEmail(String email);

    UserDto add(User user);

    UserDto updateByUsername(String username, User user);

    void delete(User user);
    void deleteByUsername(String username);

    boolean existsById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
