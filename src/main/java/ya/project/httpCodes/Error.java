package ya.project.httpCodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error {

    @JsonProperty("code")
    int code;

    @JsonProperty("message")
    String message;
}
