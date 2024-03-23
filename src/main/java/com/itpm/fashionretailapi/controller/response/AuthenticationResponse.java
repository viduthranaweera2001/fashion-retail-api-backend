package com.itpm.fashionretailapi.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthenticationResponse {

    @JsonProperty("user_id")
    private Long id;
    public Boolean authenticated;

    public AuthenticationResponse(Long id, Boolean authenticated) {
        this.id = id;
        this.authenticated = authenticated;
    }
}
