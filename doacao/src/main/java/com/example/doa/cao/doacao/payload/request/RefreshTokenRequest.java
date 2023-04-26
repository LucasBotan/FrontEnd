package com.example.doa.cao.doacao.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenRequest {

  @NotBlank
  private String refreshToken;

}
