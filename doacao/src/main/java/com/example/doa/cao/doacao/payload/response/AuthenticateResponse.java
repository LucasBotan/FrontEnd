package com.example.doa.cao.doacao.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateResponse {

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

    private String type = "Bearer";
    private String token;

    private List<String> roles;


}
