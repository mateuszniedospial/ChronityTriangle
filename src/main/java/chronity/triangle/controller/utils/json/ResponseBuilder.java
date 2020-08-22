package chronity.triangle.controller.utils.json;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface ResponseBuilder {
    <T> ResponseEntity<T> msg(T msgBody);
    ResponseEntity<String> jsonMsg(String message);
    ResponseEntity<String> noMsg();
}
