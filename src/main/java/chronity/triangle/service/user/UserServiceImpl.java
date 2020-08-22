package chronity.triangle.service.user;

import chronity.triangle.controller.utils.ResponseMsg;
import chronity.triangle.model.user.User;
import chronity.triangle.model.user.UserDto;
import chronity.triangle.repository.user.UserRepository;
import chronity.triangle.validation.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(this::createUserDto)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getByUsername(String username) {
        return createUserDto(userRepository.findByUsername(username))
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User with such username: '" + username + "' doesn't exist."
                        )
                );
    }

    @Override
    public UserDto getByEmail(String email) {
        return createUserDto(userRepository.findByEmail(email))
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User with such email: '" + email + "' doesn't exist."
                        )
                );
    }

    @Override
    public UserDto add(User user) {
//        UserValidator.validate(user);
        if (!isUsernameOrEmailTaken(user.getUsername(), user.getEmail())) {
            user.setPassword(computePasswordHash(user.getPassword()));
            return createUserDto(userRepository.insert(user)).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            new ResponseMsg<>(User.class).internalServerError(ResponseMsg.Method.POST))
            );
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User with such username or/and email already exists.");
        }
    }

    @Override
    public UserDto update(User user) {
//        UserValidator.validate(user);
        return createUserDto(userRepository.save(user)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        new ResponseMsg<>(User.class).internalServerError(ResponseMsg.Method.PUT)
                )
        );
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsernameOrEmailTaken(String username, String email) {
        return existsByUsername(username) || existsByEmail(email);
    }

    private String computePasswordHash(String toHash) {
        var bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
        return bCryptPasswordEncoder.encode(toHash);
    }

    private Optional<UserDto> createUserDto(User user) {
        return Optional.ofNullable(user)
                .map(u -> UserDto
                        .builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .isActive(u.isActive())
                        .roles(u.getRoles())
                        .build());
    }
}
