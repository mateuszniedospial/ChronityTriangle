package chronity.triangle.controller.utils.resource;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class ResourcePath implements Path {
    private final URI path;

    public ResourcePath(ServletUriComponentsBuilder currentRequestBuilder, String path, String paramValue) {
        this.path = currentRequestBuilder.path(path).buildAndExpand(paramValue).toUri();
    }

    public ResourcePath(ServletUriComponentsBuilder currentRequestBuilder, String path){
        this(currentRequestBuilder, path, "");
    }

    public ResourcePath(ServletUriComponentsBuilder currentRequestBuilder){
        this(currentRequestBuilder, "", "");
    }

    @Override
    public URI get() {
        return path;
    }
}
