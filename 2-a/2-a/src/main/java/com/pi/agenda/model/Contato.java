package com.pi.agenda.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(length = 200)
    private String email;
}
