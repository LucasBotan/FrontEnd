package com.example.doa.cao.doacao.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @JsonProperty("name")
    @NotEmpty(message = "Ops... Parece que você esqueceu do nome!")
    private String name;

    @JsonProperty("email")
    @Email(message = "Ops... Este não parece um Email válido!")
    @NotEmpty(message = "Ops... Parece que você esqueceu o Email!")
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = "Ops... Não esqueça de informar uma senha!")
    private String password;

    @JsonProperty("tel")
    private String phone;

    @JsonProperty("sexo")
    private Character gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("nasc")
    private Timestamp birth;

    private Set<String> role;

}
