package com.example.doa.cao.doacao.payload.response;

import com.example.doa.cao.doacao.models.ERole;
import com.example.doa.cao.doacao.models.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Builder
@JsonPropertyOrder(value = {"user_id", "name", "email", "tel", "sexo", "nasc", "role"})
public class RegisterResponse {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("tel")
    private String phone;

    @JsonProperty("sexo")
    private Character gender;

    @JsonProperty(value = "nasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp birth;

    private Set<String> role;

    private String refreshToken;
}
