package com.example.doa.cao.doacao.repository;

import com.example.doa.cao.doacao.models.Legal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepository extends JpaRepository<Legal, Long> {
}
