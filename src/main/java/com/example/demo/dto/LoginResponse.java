package com.example.demo.dto;

import com.example.demo.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "error",
        "timestamp",
        "data"
})
public class LoginResponse {
    @Builder.Default
    private String error = "string";

    @Builder.Default
    private Long timestamp = System.currentTimeMillis();

    @JsonProperty("data")
    private PersonDTO personData;
}
