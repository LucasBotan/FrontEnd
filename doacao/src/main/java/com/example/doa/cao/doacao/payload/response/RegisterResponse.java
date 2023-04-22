package com.example.doa.cao.doacao.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("user_name")
    private String name;

    @JsonProperty("user_email")
    private String email;

    @JsonProperty("user_phone")
    private String phone;

    @JsonProperty("user_gender")
    private String gender;

    @JsonProperty("user_birth")
    private String birth;

}
