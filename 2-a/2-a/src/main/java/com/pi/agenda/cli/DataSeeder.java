package com.pi.agenda.cli;

import com.pi.agenda.model.Contato;
import com.pi.agenda.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ContatoRepository repository;

    private static final List<Contato> SEED = List.of(
        Contato.builder().nome("Ana Souza")      .telefone("(11) 91234-5678").email("ana.souza@email.com")     .build(),
        Contato.builder().nome("Bruno Lima")     .telefone("(21) 98765-4321").email("bruno.lima@email.com")    .build(),
        Contato.builder().nome("Carla Mendes")   .telefone("(31) 99876-5432").email("carla.mendes@email.com")  .build(),
        Contato.builder().nome("Diego Ferreira") .telefone("(41) 97654-3210").email("")                        .build(),
        Contato.builder().nome("Eduarda Costa")  .telefone("(51) 96543-2109").email("eduarda.costa@email.com") .build()
    );

    @Override
    public void run(String... args) {
        SEED.forEach(seed -> {
            if (!repository.existsByNomeIgnoreCase(seed.getNome())) {
                repository.save(seed);
                log.info("Seed inserido: {}", seed.getNome());
            }
        });
    }
}
