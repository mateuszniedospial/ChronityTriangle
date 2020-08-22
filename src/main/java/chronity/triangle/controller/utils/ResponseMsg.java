package chronity.triangle.controller.utils;

import chronity.triangle.model.Dto;

public class ResponseMsg<T extends Dto> {
    public enum Method {
        GET,
        HEAD,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    private final String resourceName;

    public ResponseMsg(Class<T> resourceClass) {
        this.resourceName = resourceClass.getName().toLowerCase();
    }

    public String internalServerError(Method method) {
        var action = switch(method) {
            case POST -> " created ";
            case PUT -> " updated ";
            default -> "";
        };

        if(action.isBlank()) return "An error occurred on the server side.";
        else return "The " + resourceName + " has not been" + action + "due to an error on the server side.";
    }
}
