package com.pi.agenda.service;

import com.pi.agenda.model.Contato;
import com.pi.agenda.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgendaTelefonica {

    private final ContatoRepository repository;

    /**
     * Adiciona um novo contato. Lanca excecao se o nome ja existir.
     */
    @Transactional
    public Contato adicionarContato(String nome, String telefone, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone nao pode ser vazio.");
        }
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalStateException("Ja existe um contato com o nome: " + nome);
        }
        Contato contato = Contato.builder()
                .nome(nome.trim())
                .telefone(telefone.trim())
                .email(email != null ? email.trim() : "")
                .build();
        Contato salvo = repository.save(contato);
        log.info("Contato adicionado: {}", salvo.getNome());
        return salvo;
    }

    /**
     * Remove um contato pelo nome. Lanca excecao se nao encontrado.
     */
    @Transactional
    public void removerContato(String nome) {
        Contato contato = buscarContatoOuFalhar(nome);
        repository.delete(contato);
        log.info("Contato removido: {}", nome);
    }

    /**
     * Busca um contato pelo nome (case-insensitive). Retorna Optional.
     */
    @Transactional(readOnly = true)
    public Optional<Contato> buscarContato(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }

    /**
     * Atualiza telefone e/ou email de um contato existente.
     */
    @Transactional
    public Contato atualizarContato(String nome, String novoTelefone, String novoEmail) {
        Contato contato = buscarContatoOuFalhar(nome);
        if (novoTelefone != null && !novoTelefone.isBlank()) {
            contato.setTelefone(novoTelefone.trim());
        }
        if (novoEmail != null && !novoEmail.isBlank()) {
            contato.setEmail(novoEmail.trim());
        }
        Contato atualizado = repository.save(contato);
        log.info("Contato atualizado: {}", atualizado.getNome());
        return atualizado;
    }

    /**
     * Lista todos os contatos em ordem alfabetica de nome.
     */
    @Transactional(readOnly = true)
    public List<Contato> listarContatos() {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()))
                .toList();
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private Contato buscarContatoOuFalhar(String nome) {
        return repository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new IllegalArgumentException("Contato nao encontrado: " + nome));
    }
}
