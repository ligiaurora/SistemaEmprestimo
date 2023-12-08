package EmprestimoLivro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import com.classes.DTO.*;
import com.classes.DAO.*;
import java.util.Scanner;
import com.classes.Conexao.Conexao;

public class Main {

    public static void main(String[] args) {

        // Conexão com o Banco de Dados
        try {
            Connection conn = Conexao.conectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Scanner entrada = new Scanner(System.in);

            System.out.println("-------------------------------------------------");
            System.out.println("Bem-vindo(a) ao sistema de empréstimos de Livros!");
            System.out.println("-------------------------------------------------");

            int acao;

            do {
                System.out.print("O que deseja fazer (digite o número correspondente das ações listadas abaixo)? ");
                System.out.println("");
                System.out.println("1 - Login Atendente");
                System.out.println("2 - Login Usuário");
                System.out.println("3 - Sair");

                acao = Integer.parseInt(entrada.nextLine());

                switch (acao) {
                    case 1:
                        loginAtendente();
                        break;

                    case 2:
                        loginUsuario();
                        break;

                    case 3:
                        sairDoSistema();
                        break;

                    default:
                        System.out.println("Ação inválida! Escolha um valor válido");
                        break;
                }

            } while (acao != 3);

            entrada.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * 
     * Login Atendente
     * 
     * 
     */
    private static void loginAtendente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de usuário do atendente: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite a senha do atendente: ");
        String senha = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ExemplarLivroDAO exemplarLivroDAO = new ExemplarLivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        Atendente atendente = usuarioDAO.loginAtendente(nomeUsuario, Pessoa.criptografarSenha(senha));

        if (atendente == null) {
            System.out.println("Usuario ou Senha Errados");
            return;
        }

        int opcao;
        do {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("Menu Atendente, seja bem-vindo(a)");
            System.out.println("------------------------------------");
            System.out.println("O que deseja fazer? Digite o número correspondente das ações listadas abaixo:");
            System.out.println("");

            System.out.println("1 - Cadastrar Atendente");
            System.out.println("2 - Cadastrar Usuário");
            System.out.println("3 - Desativar Atendente");
            System.out.println("4 - Desativar Usuário");
            System.out.println("5 - Listar Atendentes");
            System.out.println("6 - Listar Usuários");
            System.out.println("7 - Adicionar Exemplar");
            System.out.println("8 - Remover Exemplar");
            System.out.println("9 - Listar Exemplares do Arquivo");
            System.out.println("10 - Realizar Empréstimo");
            System.out.println("11 - Renovar Empréstimo");
            System.out.println("12 - Adicionar Multa");
            System.out.println("13 - Remover Multa");
            System.out.println("14 - Listar Livros Emprestados");
            System.out.println("15 - Reativar Atendente");
            System.out.println("16 - Reativar Usuário");
            System.out.println("17 - Alterar Atendente");
            System.out.println("18 - Alterar Usuário");
            System.out.println("19 - Excluir Atendente");
            System.out.println("20 - Excluir Usuario");
            System.out.println("21 - Sair");

            opcao = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (opcao) {
                case 1:
                    usuarioDAO.addAtendente();
                    break;

                case 2:
                    usuarioDAO.addUsuario();
                    break;

                case 3:
                    System.out.print("Digite o Id do Atendente que você deseja desativar: ");
                    int idAtendenteDesativar = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.desativarAtendente(idAtendenteDesativar)) {
                        System.out.println("Atendente Desativado");
                    } else {
                        System.out.println("Não foi possível desativar o atendente. Verifique o Id informado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o Id do Usuário que você deseja desativar: ");
                    int idUsuarioDesativar = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.desativarAtendente(idUsuarioDesativar)) {
                        System.out.println("Usuário Desativado");
                    } else {
                        System.out.println("Não foi possível desativar o usuário. Verifique o Id informado.");
                    }
                    break;

                case 5:
                    UsuarioDAO usuarioD = new UsuarioDAO();
                    List<Atendente> atendentes = usuarioD.listarAtendentes();
                    if (atendentes.isEmpty()) {
                        System.out.println("Nenhum atendente cadastrado.");
                    } else {
                        System.out.println("--------------------");
                        System.out.println("Lista de Atendentes");
                        System.out.println("--------------------");
                        System.out.println("");
                        for (Atendente atendenteLista : atendentes) {
                            System.out.println(atendenteLista.toString());
                        }
                    }
                    break;

                case 6:
                    UsuarioDAO usuarioU = new UsuarioDAO();
                    List<Usuario> user = usuarioU.listarUsuarios();
                    if (user.isEmpty()) {
                        System.out.println("Nenhum Usuário cadastrado.");
                    } else {
                        System.out.println("--------------------");
                        System.out.println("Lista de Usuários");
                        System.out.println("--------------------");
                        for (Usuario UsuarioLista : user) {
                            System.out.println(UsuarioLista.toString());
                        }
                    }
                    break;

                case 7:
                    exemplarLivroDAO.editarExemplar();
                    break;

                case 8:
                    exemplarLivroDAO.RemoverExemplar();
                    break;

                case 9:
                    ExemplarLivroDAO exemplarU = new ExemplarLivroDAO();
                    List<ExemplarLivro> eLivros = exemplarU.pesquisarTodos();
                    if (eLivros.isEmpty()) {
                        System.out.println("Nenhum Livro Cadastrado");
                    } else {
                        System.out.println("--------------------");
                        System.out.println("Lista de Livros");
                        System.out.println("--------------------");
                        for (ExemplarLivro exemplar : eLivros) {
                            System.out.println(exemplar.toString());
                        }
                    }
                    break;

                case 10:
                    System.out.print("Digite o ID do Exemplar: ");
                    int idExemplar = Integer.parseInt(new Scanner(System.in).nextLine());

                    System.out.print("Digite o ID do Usuário: ");
                    int idUsuario = Integer.parseInt(new Scanner(System.in).nextLine());

                    emprestimoDAO.realizarEmprestimo(idExemplar, idUsuario);
                    break;

                case 11:
                    emprestimoDAO.renovarEmprestimo();
                    break;

                case 12:
                    emprestimoDAO.AdicionarMulta();
                    break;

                case 13:
                    emprestimoDAO.AdicionarMulta();
                    break;

                case 14:
                    System.out.println("----------------------");
                    System.out.println(" Lista de Emprestimos ");
                    System.out.println("----------------------");
                    List<Emprestimo> emprestimos = emprestimoDAO.procurarPorCodigo(atendente.getId());
                    for (Emprestimo emprestimo : emprestimos) {
                        System.out.println(emprestimo.toString());
                    }
                    break;

                case 15:
                    System.out.print("Digite o Id do Atendente que você deseja reativar: ");
                    int idAtendenteReativar = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.reativarAtendente(idAtendenteReativar)) {
                        System.out.println("Atendente Reativado");
                    } else {
                        System.out.println("Não foi possível reativar o atendente. Verifique o Id informado.");
                    }
                    break;

                case 16:
                    System.out.print("Digite o Id do Usuário que você deseja reativar: ");
                    int idUsuarioReativar = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.reativarUsuario(idUsuarioReativar)) {
                        System.out.println("Usuário Reativado");
                    } else {
                        System.out.println("Não foi possível reativar o usuário. Verifique o Id informado.");
                    }
                    break;

                case 17:
                	System.out.println("-----------------");
                    System.out.println("Alterar Atendente");
                    System.out.println("-----------------");
                    System.out.print("Digite o ID do atendente que você deseja alterar: ");
                    int idAtendenteAlterar = Integer.parseInt(new Scanner(System.in).nextLine());
                    
                    System.out.print("Digite o novo nome: ");
                    String novoNome = new Scanner(System.in).nextLine();
                    
                    System.out.print("Digite o novo nome de usuário: ");
                    String novoNomeAtendente = new Scanner(System.in).nextLine();
                    
                    System.out.print("Digite a nova senha: ");
                    String novaSenha = new Scanner(System.in).nextLine();
                    
                    if (usuarioDAO.alterarAtendente(idAtendenteAlterar, novoNome, novoNomeAtendente, novaSenha)) {
                        System.out.println("Atendente alterado com sucesso!");
                    } else {
                        System.out.println("Não foi possível alterar o atendente. Verifique o ID informado.");
                    }
                    break;

                case 18:
                	System.out.println("-----------------");
                    System.out.println("Alterar Usuário");
                    System.out.println("-----------------");
                    System.out.print("Digite o ID do atendente que você deseja alterar: ");
                    int idUsuarioAlterar = Integer.parseInt(new Scanner(System.in).nextLine());
                    
                    System.out.print("Digite o novo nome: ");
                    String novoNomeU = new Scanner(System.in).nextLine();
                    
                    System.out.print("Digite o novo nome de usuário: ");
                    String novoNomeUsuario = new Scanner(System.in).nextLine();
                    
                    System.out.print("Digite a nova senha: ");
                    String novaSenhaU = new Scanner(System.in).nextLine();
                    
                    if (usuarioDAO.alterarAtendente(idUsuarioAlterar, novoNomeU, novoNomeUsuario, novaSenhaU)) {
                        System.out.println("Atendente alterado com sucesso!");
                    } else {
                        System.out.println("Não foi possível alterar o atendente. Verifique o ID informado.");
                    }
                    break;

                case 19:
                    System.out.println("------------------");
                    System.out.println("Excluir Atendente");
                    System.out.println("------------------");
                    System.out.print("Digite o Id do Atendente que você deseja excluir: ");
                    int idAtendenteExcluir = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.excluirAtendente(idAtendenteExcluir)) {
                        System.out.println("Atendente Excluído");
                    } else {
                        System.out.println("Não foi possível excluir o atendente. Verifique o Id informado.");
                    }
                    break;

                case 20:
                	System.out.println("------------------");
                    System.out.println("Excluir Usuário");
                    System.out.println("------------------");
                    System.out.print("Digite o Id do Usuário que você deseja excluir: ");
                    int idUsuarioExcluir = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (usuarioDAO.excluirUsuario(idUsuarioExcluir)) {
                        System.out.println("Usuário Excluído");
                    } else {
                        System.out.println("Não foi possível excluir o usuário. Verifique o Id informado.");
                    }
                    break;


                case 21:
                    sairDoSistema();
                    break;
            }
        } while (opcao != 21);
    }

    /*
     * Informação do Usuário
     * 
     */
    private static void loginUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de Usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite a sua senha de Usuario: ");
        String senha = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        Usuario user = usuarioDAO.loginUsuario(nomeUsuario, Pessoa.criptografarSenha(senha));

        if (user == null) {
            System.out.println("Usuario ou Senha Errados");
            return;
        }
        int opcao;

        do {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("Menu Usuário(a), seja bem-vindo(a)");
            System.out.println("------------------------------------");
            System.out.println("Opções do Usuário:");
            System.out.println("1 - Lista livros emprestados");
            System.out.println("2 - Verificar multas");
            System.out.println("3 - Sair");

            opcao = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (opcao) {
                case 1:
                    List<Emprestimo> emprestimos = emprestimoDAO.procurarPorCodigo(user.getId());
                    for (Emprestimo emprestimo : emprestimos) {
                        System.out.println(emprestimo.toString());
                    }
                    break;

                case 2:
                    double valor_multa = 0;
                    List<Emprestimo> emprestimosmulta = emprestimoDAO.procurarPorCodigo(user.getId());
                    for (Emprestimo emprestimo : emprestimosmulta) {
                        valor_multa += emprestimo.getValorMulta();
                    }
                    System.out.println("Valor da multa: " + valor_multa);
                    break;

                case 3:
                    sairDoSistema();
                    break;
            }

        } while (opcao != 3);
    }
    /*
     * Metodo para opção de sair do Sistema
     */

    private static void sairDoSistema() {
        System.out.println("Saindo do sistema. Até logo!");
        System.exit(0);
    }

}
