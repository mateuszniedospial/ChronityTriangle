package chronity.triangle.config;

public interface ApiPaths {
    interface Users {
        String ROOT = "/users";
        String BY_USERNAME = "/{username}";
        String BY_EMAIL = "/{email}";
    }
}
