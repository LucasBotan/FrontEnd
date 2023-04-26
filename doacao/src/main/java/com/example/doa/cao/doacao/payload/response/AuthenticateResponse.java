package com.example.doa.cao.doacao.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"user_id", "name", "email", "tel", "sexo", "nasc", "role"})
public class AuthenticateResponse {

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

    @JsonProperty("nasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp birth;

    private String token;



    private List<String> roles;


}
