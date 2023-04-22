package com.example.doa.cao.doacao.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Ops... Parece que você esqueceu do nome!")
    private String name;

    @NotEmpty(message = "Ops... Parece que você esqueceu o Email!")
    @Email(message = "Ops... Este não parece um Email válido!")
    private String email;

    @NotEmpty(message = "Ops... Não esqueça de informar uma senha!")
    private String password;

    private String phone;

    private String gender;

    private String birth;

    private Set<String> role;

}
