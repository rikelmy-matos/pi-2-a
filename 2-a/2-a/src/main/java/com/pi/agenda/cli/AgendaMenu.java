package com.pi.agenda.cli;

import com.pi.agenda.model.Contato;
import com.pi.agenda.service.AgendaTelefonica;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgendaMenu implements CommandLineRunner {

    private final AgendaTelefonica agenda;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        System.out.println("============================================");
        System.out.println("       AGENDA TELEFONICA — PI II-A          ");
        System.out.println("============================================");

        boolean executando = true;
        while (executando) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            System.out.println();

            switch (opcao) {
                case "1" -> adicionarContato();
                case "2" -> removerContato();
                case "3" -> buscarContato();
                case "4" -> atualizarContato();
                case "5" -> listarContatos();
                case "6" -> {
                    System.out.println("Encerrando o sistema. Ate logo!");
                    executando = false;
                }
                default -> System.out.println("[AVISO] Opcao invalida. Tente novamente.\n");
            }
        }
    }

    // -------------------------------------------------------------------------
    // Menu
    // -------------------------------------------------------------------------

    private void exibirMenu() {
        System.out.println("--------------------------------------------");
        System.out.println(" 1. Adicionar contato");
        System.out.println(" 2. Remover contato");
        System.out.println(" 3. Buscar contato");
        System.out.println(" 4. Atualizar contato");
        System.out.println(" 5. Listar todos os contatos");
        System.out.println(" 6. Sair");
        System.out.println("--------------------------------------------");
        System.out.print("Escolha uma opcao: ");
    }

    // -------------------------------------------------------------------------
    // Operacoes
    // -------------------------------------------------------------------------

    private void adicionarContato() {
        System.out.println("--- ADICIONAR CONTATO ---");
        String nome = lerCampo("Nome");
        String telefone = lerCampo("Telefone");
        String email = lerCampoOpcional("E-mail (opcional, pressione Enter para pular)");

        try {
            Contato salvo = agenda.adicionarContato(nome, telefone, email);
            System.out.println("[OK] Contato adicionado com sucesso!");
            imprimirContato(salvo);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
        System.out.println();
    }

    private void removerContato() {
        System.out.println("--- REMOVER CONTATO ---");
        String nome = lerCampo("Nome do contato a remover");

        try {
            agenda.removerContato(nome);
            System.out.println("[OK] Contato \"" + nome + "\" removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
        System.out.println();
    }

    private void buscarContato() {
        System.out.println("--- BUSCAR CONTATO ---");
        String nome = lerCampo("Nome do contato");

        Optional<Contato> resultado = agenda.buscarContato(nome);
        if (resultado.isPresent()) {
            System.out.println("[OK] Contato encontrado:");
            imprimirContato(resultado.get());
        } else {
            System.out.println("[INFO] Nenhum contato encontrado com o nome: " + nome);
        }
        System.out.println();
    }

    private void atualizarContato() {
        System.out.println("--- ATUALIZAR CONTATO ---");
        String nome = lerCampo("Nome do contato a atualizar");
        System.out.println("(Pressione Enter para manter o valor atual)");
        String novoTelefone = lerCampoOpcional("Novo telefone");
        String novoEmail = lerCampoOpcional("Novo e-mail");

        try {
            Contato atualizado = agenda.atualizarContato(nome, novoTelefone, novoEmail);
            System.out.println("[OK] Contato atualizado com sucesso!");
            imprimirContato(atualizado);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
        System.out.println();
    }

    private void listarContatos() {
        System.out.println("--- LISTA DE CONTATOS ---");
        List<Contato> contatos = agenda.listarContatos();

        if (contatos.isEmpty()) {
            System.out.println("[INFO] Nenhum contato cadastrado.");
        } else {
            System.out.printf("%-5s %-30s %-20s %-30s%n", "ID", "NOME", "TELEFONE", "E-MAIL");
            System.out.println("-".repeat(90));
            contatos.forEach(c ->
                System.out.printf("%-5d %-30s %-20s %-30s%n",
                        c.getId(), c.getNome(), c.getTelefone(), c.getEmail())
            );
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // Helpers de leitura
    // -------------------------------------------------------------------------

    private String lerCampo(String label) {
        String valor;
        do {
            System.out.print(label + ": ");
            valor = scanner.nextLine().trim();
            if (valor.isBlank()) {
                System.out.println("[AVISO] Este campo e obrigatorio.");
            }
        } while (valor.isBlank());
        return valor;
    }

    private String lerCampoOpcional(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    private void imprimirContato(Contato c) {
        System.out.println("  ID       : " + c.getId());
        System.out.println("  Nome     : " + c.getNome());
        System.out.println("  Telefone : " + c.getTelefone());
        System.out.println("  E-mail   : " + c.getEmail());
    }
}
