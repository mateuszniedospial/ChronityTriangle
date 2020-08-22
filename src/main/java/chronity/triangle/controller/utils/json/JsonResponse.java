package chronity.triangle.controller.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class JsonResponse {
    private static final String RESULT = "result";

    private JsonResponse() {}

    public static ResponseBuilder status(HttpStatus status) {
       return new DefaultResponseBuilder(status);
    }

    private static class DefaultResponseBuilder implements ResponseBuilder {
        private final HttpStatus status;

        DefaultResponseBuilder(HttpStatus status) {
            this.status = status;
        }

        public <T> ResponseEntity<T> msg(T msgBody) {
            return ResponseEntity.status(status).body(msgBody);
        }

        public ResponseEntity<String> jsonMsg(String message) {
            var httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity
                    .status(status)
                    .headers(httpHeaders)
                    .body(mapToJson(message));
        }

        @Override
        public ResponseEntity<String> noMsg() {
            return ResponseEntity
                    .status(status)
                    .build();
        }

        private String mapToJson(String msg) {
            var objectMapper = new ObjectMapper();
            var resultNode = objectMapper.createObjectNode();

            resultNode.put(RESULT, msg);

            try {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
            } catch (JsonProcessingException e) {
                e.printStackTrace(); //TODO Replace with logging on the API level sl4j or sth
                return msg;
            }
        }
    }
}
