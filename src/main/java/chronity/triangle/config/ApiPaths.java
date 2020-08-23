package chronity.triangle.config;

public interface ApiPaths {
    interface Users {
        String ROOT = "/users";
        String BY_USERNAME = "/{username}";
        String BY_EMAIL = "/{email}";

        interface PathParams {
            String USERNAME = "username";
        }
    }

    interface Artists {
        String ROOT = "/artists";
        String BY_ID = "/{id}";

        interface PathParams {
            String ID = "id";
        }

        interface QueryParams {
            String USER_ID = "userId";
        }
    }
}
