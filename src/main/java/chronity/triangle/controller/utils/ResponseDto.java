package chronity.triangle.controller.utils;

import chronity.triangle.controller.utils.resource.Path;
import chronity.triangle.controller.utils.resource.ResourcePath;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Getter
@ToString
@EqualsAndHashCode
public final class ResponseDto<T> {
    private final T result;
    private final URI path;

    private ResponseDto(T result, Path path) {
        this.result = result;
        this.path = path.get();
    }

    public static <T> ResponseDto<T> create(T result, ServletUriComponentsBuilder requestUri, String path, String param) {
        var resourcePath = new ResourcePath(requestUri, path, param);
        return new ResponseDto<>(result, resourcePath);
    }

    public static <T> ResponseDto<T> create(T result, ServletUriComponentsBuilder requestUri, String path) {
        return create(result, requestUri, path, "");
    }

    public static <T> ResponseDto<T> create(T result, ServletUriComponentsBuilder requestUri) {
        return create(result, requestUri, "", "");
    }
}
