package chronity.triangle.controller.utils.resource;

import java.net.URI;

@FunctionalInterface
public interface Path {
    URI get();
}
