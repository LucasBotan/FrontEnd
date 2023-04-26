package com.example.doa.cao.doacao.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
