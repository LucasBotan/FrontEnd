package com.example.doa.cao.doacao.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

  @GetMapping
  public ResponseEntity<String> getHome() {
    return ResponseEntity.ok(" ");
  }

}
