package chronity.triangle.model.user;

import chronity.triangle.model.Dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDto implements Dto {
    private String id;
    private String username;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private boolean isActive;
    private List<String> roles;

    private List<String> artistsId;
}
