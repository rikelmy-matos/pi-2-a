package com.pi.agenda.repository;

import com.pi.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}
