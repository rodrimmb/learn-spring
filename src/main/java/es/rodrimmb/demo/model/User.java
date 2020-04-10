package es.rodrimmb.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
@Value
public class User {

    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("name")
    @NotBlank
    private final String name;

}
