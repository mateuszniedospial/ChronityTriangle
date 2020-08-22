package chronity.triangle.model.user;

import chronity.triangle.model.Dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class User implements Dto {
    @Id
    private String id;

    @Indexed(name = "username_index_unique", unique = true)
    private String username;

    private String password;

    @Indexed(name = "email_index_unique", unique = true)
    private String email;

    private LocalDate creationDate;
    private boolean isActive;

    private List<String> roles;

    private List<String> artistsId;

    public User(String username,
                String password,
                String email,
                LocalDate creationDate,
                boolean isActive,
                List<String> roles,
                List<String> artistsId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = creationDate;
        this.isActive = isActive;
        this.roles = roles;
        this.artistsId = artistsId;
    }
}
