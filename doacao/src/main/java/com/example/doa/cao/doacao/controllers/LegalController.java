package com.example.doa.cao.doacao.controllers;

import com.example.doa.cao.doacao.models.Legal;
import com.example.doa.cao.doacao.repository.LegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/legal")
public class LegalController {

    @Autowired
    private LegalRepository repository;

    @GetMapping("/terms")
    public ResponseEntity<?>termsOfUse(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/term/{id}")
    public ResponseEntity<?>termOfUse(@PathVariable("id") Long id){
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>createTerm(@RequestBody LegalRequest legalRequest){
        Legal legal = new Legal();
        legal.setText(legalRequest.text());
        repository.save(legal);
        return ResponseEntity.ok(legal);
    }

    @PutMapping("/term/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTerm(@PathVariable("id") Long id, @RequestBody LegalRequest legalRequest){
        Legal legal = repository.findById(id).orElseThrow(() -> new RuntimeException("id não encontrado!"));
        legal.setText(legalRequest.text());
        return ResponseEntity.ok(repository.save(legal));
    }

    @DeleteMapping("/term/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTerm(@PathVariable("id") Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    record LegalRequest(String text){}
}
