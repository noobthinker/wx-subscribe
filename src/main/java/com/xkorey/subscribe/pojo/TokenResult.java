package com.xkorey.subscribe.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResult {
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("expires_in")
    Integer expiresIn;
}
