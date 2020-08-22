package chronity.triangle.model.user;

import chronity.triangle.model.Dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private boolean isActive;

    private List<String> roles;

    private List<String> musicsId;

    public User(String username,
                String password,
                String email,
                boolean isActive,
                List<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.roles = roles;
    }
}
