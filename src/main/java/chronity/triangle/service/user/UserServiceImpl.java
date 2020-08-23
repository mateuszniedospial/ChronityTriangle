package chronity.triangle.service.user;

import chronity.triangle.controller.utils.ResponseMsg;
import chronity.triangle.model.user.User;
import chronity.triangle.model.user.UserDto;
import chronity.triangle.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        //TODO batches
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
        //TODO add user (request body) validation
        //UserValidator.validate(user);
        if (!isUsernameOrEmailTaken(user.getUsername(), user.getEmail())) {
            var userToInsert = initializeForInsert(user);
            return createUserDto(userRepository.insert(userToInsert)).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            new ResponseMsg<>(User.class).internalServerError(ResponseMsg.Method.POST))
            );
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User with such username or/and email already exists.");
        }
    }

    @Override
    public UserDto updateByUsername(String username, User user) {
        //TODO add user (request body) validation
        //UserValidator.validate(user);
        return Optional.ofNullable(userRepository.findByUsername(username))
                .flatMap(foundUser -> createUserDto(userRepository.save(createUserForUpdate(foundUser, user))))
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        new ResponseMsg<>(User.class).internalServerError(ResponseMsg.Method.PUT))
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
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isUsernameOrEmailTaken(String username, String email) {
        return existsByUsername(username) || existsByEmail(email);
    }

    private User createUserForUpdate(User foundUser, User requestBody) {
        if (!isUsernameOrEmailTaken(requestBody.getUsername(), requestBody.getEmail())){
            return updateEntity(foundUser, requestBody);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Username or/and email passed in request body are already taken. Cannot update the user.");
        }
    }

    private User initializeForInsert(User user) {
        user.setCreationDate(LocalDateTime.now());
        user.setLastUpdateDate(LocalDateTime.now());
        user.setActive(false);
        user.setPassword(computePasswordHash(user.getPassword()));
        return user;
    }

    //TODO what to do with boolean fields - false as default
    public User updateEntity(User foundUser, User toUpdateUser) {
        Optional.ofNullable(toUpdateUser.getUsername()).ifPresent(foundUser::setUsername);
        Optional.ofNullable(toUpdateUser.getEmail()).ifPresent(foundUser::setEmail);
        Optional.ofNullable(toUpdateUser.getPassword()).ifPresent(value -> foundUser.setPassword(computePasswordHash(value)));
        Optional.ofNullable(toUpdateUser.getRoles()).ifPresent(foundUser::setRoles);
        Optional.ofNullable(toUpdateUser.getArtistsId()).ifPresent(foundUser::setArtistsId);
        foundUser.setLastUpdateDate(LocalDateTime.now());
        return foundUser;
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
                        .creationDate(u.getCreationDate())
                        .lastUpdateDate(u.getLastUpdateDate())
                        .isActive(u.isActive())
                        .roles(u.getRoles())
                        .artistsId(u.getArtistsId())
                        .build());
    }
}
