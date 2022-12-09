package tema8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static boolean fgLoggado = false;
    private static Usuario usuarioAtual;

    private static Biblioteca biblioteca = new Biblioteca();
    private static BibliotecaDAO bibliotecaDAO = new BibliotecaDAO(biblioteca);
    private static Clientela clientela = new Clientela();
    private static ClientelaDAO clientelaDAO = new ClientelaDAO(clientela);
    private static HistoricoEmprestimos historicoEmprestimos = new HistoricoEmprestimos(biblioteca, clientela);
    private static HistoricoEmprestimosDAO historicoEmprestimosDAO = new HistoricoEmprestimosDAO(biblioteca, clientela,
            bibliotecaDAO, clientelaDAO, historicoEmprestimos);

    public static boolean isFgLoggado() {
        return fgLoggado;
    }

    public static void setFgLoggado(boolean fgLoggado) {
        Main.fgLoggado = fgLoggado;
    }

    public static Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public static void setUsuarioAtual(Usuario usuarioAtual) {
        Main.usuarioAtual = usuarioAtual;
    }

    public static void imprimeColecaoDeLivros(ArrayList<Livro> biblioteca) {
        if (biblioteca.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (Livro livro : biblioteca) {
                livro.printLivro();
                System.out.println();
            }
        }
    }

    public static void imprimeRelatorioLivroEmprestadoXCliente() {
        ArrayList<Emprestimo> emprestimosLocados = historicoEmprestimosDAO.relatorioLivrosEmprestados();
        if (emprestimosLocados.isEmpty()) {
            System.out.println("Nenhum livro está emprestado");
        } else {
            for (Emprestimo emprestimo : emprestimosLocados) {
                System.out.println("Título: " + emprestimo.getLivro().getTitulo());
                System.out.println("Autor: " + emprestimo.getLivro().getAutor());
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
                System.out.println();
            }
        }
    }

    public static void imprimeRelatorioUsuariosAtrasados() {
        ArrayList<Emprestimo> emprestimosAtrasados = historicoEmprestimosDAO.relatorioEmprestimosAtrasados();
        if (emprestimosAtrasados.isEmpty()) {
            System.out.println("Nenhum livro está atrasado");
        } else {
            for (Emprestimo emprestimoAtrasado : emprestimosAtrasados) {
                System.out.println("Usuário: " + emprestimoAtrasado.getUsuario().getNome() + " atrasou por " + emprestimoAtrasado.getQtDiasAtraso() + " dia(s) a entrega do livro \"" + emprestimoAtrasado.getLivro().getTitulo() + "\".");
            }
        }
    }

    public static void imprimeTop10Usuarios(ArrayList<Usuario> topUsuarios) {
        System.out.println("AGORA COM VOCÊS, OS 10 USUARIOS QUE MAIS LOCARAM LIVROS DESDE SEMPRE:");
        int i = 1;
        System.out.println("\t\t\tNome\t\t Qt de Livros");
        for (Usuario usuario : topUsuarios) {
            System.out.println(i + "° lugar: " + usuario.getNome() + "\t--------- " + usuario.getQtLivrosLocadosDesdeSempre());
            i++;
            if(i > 10) {
                break;
            }
        }
    }

    public static void menuPrincipal() {
        historicoEmprestimosDAO.carregarTodos();

        Scanner teclado = new Scanner(System.in);
        int op = 1;
        while (op != 0 ) {
            System.out.println("------------- MENU PRINCIPAL ------------");
            System.out.println("\nDigite (1) se você é cliente,\n       (2) se é funcionário\n       (0) para sair.");
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuFuncionario();
                    break;
                default:
                    System.out.println("Número inválido.");
            }
        }
    }

    public static void loginCliente() {
        Scanner teclado = new Scanner(System.in);
        int op = 1;

        System.out.println("Bom (dia inteiro)! Você já possui cadastro em nossa Biblioteca?!\n\t(1) SIM\n\t(2) NÃO");
        System.out.println("\nCaso não deseje fazer loggin, digite (0) para sair.");
        while(!isFgLoggado() && op != 0) {
            op = teclado.nextInt();
            teclado.nextLine();
            if (op == 1) {
                System.out.println("Por favor, digite seu identificador para que possamos lhe buscar no Sistema:");
                int id = teclado.nextInt();
                teclado.nextLine();
                usuarioAtual = clientela.buscaUsuarioPorID(id).get();
                if (getUsuarioAtual() != null) {
                    setFgLoggado(true);
                }
            } else if (op == 2) {
                System.out.println("Vamos cadastrá-lo em nosso estabelecimento! Será rápido.");
                System.out.println("Digite seu nome:");
                String nome = teclado.nextLine();
                System.out.println("Agora digite seu endereço:");
                String endereco = teclado.nextLine();
                System.out.println("Quantos anos você tem?");
                int idade = teclado.nextInt();
                teclado.nextLine();
                usuarioAtual = new Usuario(nome, endereco, idade);
                clientelaDAO.addUsuario(usuarioAtual);
                System.out.println(nome + ", obrigade por escolher nossos serviços!");
                setFgLoggado(true);
            }
        }
    }

    public static void menuCliente() {
        Scanner teclado = new Scanner(System.in);
        int op = 1;
        while (op != 0 ) {
            System.out.println("------------- MENU CLIENTE ------------");
            System.out.println("Digite: \n(1) para listar livros,\n(2) para buscar livro\n(3) para locar livro");
            System.out.println("(4) para devolver livro,\n(5) para renovar livro,\n(6) para quitar dívida");
            System.out.println("(7) para ver top 10 dos usuários ou\n(0) para voltar ao menu anterior.");
            op = teclado.nextInt();
            teclado.nextLine();
            int id;

            switch (op) {
                case 1:
                    imprimeColecaoDeLivros(bibliotecaDAO.retornaBiblioteca());
                    break;
                case 2:
                    System.out.println("Deseja buscar o livro por:\n(1) título;\n(2) autor");
                    int op2 = teclado.nextInt();
                    teclado.nextLine();
                    if (op2 == 1) {
                        System.out.println("Digite o título a ser buscado:");
                        String titulo = teclado.nextLine();
                        ArrayList<Livro> livrosBuscados = biblioteca.buscaLivroPorTitulo(titulo);
                        if (!livrosBuscados.isEmpty()) {
                            imprimeColecaoDeLivros(livrosBuscados);
                        } else {
                            System.out.println("Nenhum livro com este título foi encontrado.");
                        }
                    } else if (op2 == 2) {
                        System.out.println("Digite o autor a ser buscado:");
                        String autor = teclado.nextLine();
                        ArrayList<Livro> livrosBuscados = biblioteca.buscaLivroPorAutor(autor);
                        if (!livrosBuscados.isEmpty()) {
                            imprimeColecaoDeLivros(livrosBuscados);
                        } else {
                            System.out.println("Não foi encontrado nenhum livro com este autor.");
                        }
                    }
                    break;
                case 3:
                    if (!isFgLoggado()) {
                        loginCliente();
                    }
                    System.out.println("Digite o id do livro que deseja locar:");
                    id = teclado.nextInt();
                    teclado.nextLine();
                    Optional<Livro> livroAEmprestar = biblioteca.buscaLivroPorId(id);
                    if (livroAEmprestar.isPresent()) {
                        historicoEmprestimosDAO.emprestar(livroAEmprestar.get(), Main.usuarioAtual);
                    } else {
                        System.out.println("id de livro incorreto");
                    }
                    break;
                case 4:
                    if (!isFgLoggado()) {
                        loginCliente();
                    }
                    System.out.println("Digite o id do livro que deseja devolver:");
                    id = teclado.nextInt();
                    teclado.nextLine();
                    Optional<Livro> livroADevolver = biblioteca.buscaLivroPorId(id);
                    if (livroADevolver.isEmpty()) {
                        System.out.println("Livro não encontrado");
                        break;
                    }
                    Optional<Emprestimo> emprestimoADevolver = historicoEmprestimos.buscaEmprestimoPorUsuarioELivro(livroADevolver.get(), usuarioAtual);
                    if (emprestimoADevolver.isEmpty()){
                        System.out.println("Este livro não foi emprestado para ti.");
                        break;
                    }
                    historicoEmprestimosDAO.devolver(emprestimoADevolver.get());
                    break;
                case 5:
                    if (!isFgLoggado()) {
                        loginCliente();
                    }
                    System.out.println("Digite o id do livro que vai renovar:");
                    id = teclado.nextInt();
                    teclado.nextLine();
                    Optional<Livro> livroARenovar = biblioteca.buscaLivroPorId(id);
                    if (livroARenovar.isEmpty()) {
                        System.out.println("Livro não encontrado");
                        break;
                    }
                    Optional<Emprestimo> emprestimoARenovar = historicoEmprestimos.buscaEmprestimoPorUsuarioELivro(livroARenovar.get(), usuarioAtual);
                    if (emprestimoARenovar.isEmpty()){
                        System.out.println("Este livro não foi emprestado para ti.");
                        break;
                    }
                    historicoEmprestimosDAO.renovar(emprestimoARenovar.get());
                    break;
                case 6:
                    if (!isFgLoggado()) {
                        loginCliente();
                    }
                    System.out.println("Sua multa tem o valor total de R$ " + usuarioAtual.getSaldoDevedor() + ",00.");
                    System.out.println("Digite aqui o quanto vai pagar:");
                    int pagou = teclado.nextInt();
                    teclado.nextLine();
                    clientelaDAO.pagouQtSaldo(usuarioAtual,pagou);

                    break;
                case 7:
                    imprimeTop10Usuarios(clientela.topUsuarios());
                    break;
                default:
                    System.out.println("Número inválido.");
            }
        }
    }

    public static void menuFuncionario() {
        Scanner teclado = new Scanner(System.in);
        int op = 1;
        while (op != 0 ) {
            System.out.println("------------- MENU FUNCIONARIO ------------");
            System.out.println("Digite: \n(1) para cadastrar livro,\n(2) para excluir livro\n(3) para ver o relatório de livros emprestados x cliente");
            System.out.println("(4) para ver o relatório de clientes atrasados e quanto dias de atraso por livro,\n(5) para ver top 10 dos usuários ou\n(0) para voltar ao menu anterior.");
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1:
                    Livro livroACadastrar = new Livro();
                    System.out.println("Título:");
                    String texto = teclado.nextLine();
                    livroACadastrar.setTitulo(texto);
                    System.out.println("Autor:");
                    texto = teclado.nextLine();
                    livroACadastrar.setAutor(texto);

                    bibliotecaDAO.cadastrarLivro(livroACadastrar);
                    break;
                case 2:
                    System.out.println("Digite o ID do livro a ser deletado: ");
                    int idADeletar = teclado.nextInt();
                    teclado.nextLine();
                    Livro livroADeletar = biblioteca.buscaLivroPorId(idADeletar).get();
                    if (livroADeletar != null) {
                        bibliotecaDAO.excluirLivro(livroADeletar);
                    } else {
                        System.out.println("id inexistente");
                    }
                    break;
                case 3:
                    imprimeRelatorioLivroEmprestadoXCliente();
                    break;
                case 4:
                    imprimeRelatorioUsuariosAtrasados();
                    break;
                case 5:
                    imprimeTop10Usuarios(clientela.topUsuarios());
                    break;
                default:
                    System.out.println("Número inválido.");
            }
        }
    }


    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Clientela clientela = new Clientela();
        HistoricoEmprestimos historicoEmprestimos = new HistoricoEmprestimos(biblioteca, clientela);

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO(biblioteca);
        ClientelaDAO clientelaDAO = new ClientelaDAO(clientela);
        HistoricoEmprestimosDAO historicoEmprestimosDAO = new HistoricoEmprestimosDAO(biblioteca, clientela, bibliotecaDAO,
                clientelaDAO, historicoEmprestimos);

        historicoEmprestimosDAO.carregarTodos();

        Usuario user1 = new Usuario("isabella");
        Usuario user2 = new Usuario("testeoi");
        Usuario user3 = new Usuario("Jamuare");
        clientelaDAO.addUsuario(user1);
        clientelaDAO.addUsuario(user2);
        clientelaDAO.addUsuario(user3);

        Livro livro1 = new Livro("capa preta");
        Livro livro2 = new Livro("Crime e Castigo", "Dostoievski");
        bibliotecaDAO.cadastrarLivro(livro1);
        bibliotecaDAO.cadastrarLivro(livro2);

        historicoEmprestimosDAO.emprestar(livro1, user1);

        Optional<Emprestimo> emprestimo = historicoEmprestimos.buscaEmprestimoPorUsuarioELivro(livro1,user1);
        if (emprestimo.isPresent()) {

            System.out.println(1);
            historicoEmprestimosDAO.renovar(emprestimo.get());
            imprimeRelatorioLivroEmprestadoXCliente();

            System.out.println(2);
            historicoEmprestimosDAO.devolver(emprestimo.get());
            imprimeRelatorioLivroEmprestadoXCliente();
        }

        System.out.println(3);
        Optional<Emprestimo> emprestimo1 = historicoEmprestimos.emprestar(livro1, user2);
        Optional<Emprestimo> emprestimo2 = historicoEmprestimos.emprestar(livro2, user2);
        imprimeRelatorioLivroEmprestadoXCliente();

        if(emprestimo1.isPresent()) {
            historicoEmprestimosDAO.devolver(emprestimo1.get());
        }

        if(emprestimo2.isPresent()) {
            historicoEmprestimosDAO.devolver(emprestimo2.get());
        }

        Optional<Emprestimo> emprestimoAtrasado = historicoEmprestimos.emprestar(livro2,user2);
        if(emprestimoAtrasado.isPresent()) {
            emprestimoAtrasado.get().setDataLocacao(LocalDate.now().minusDays(8));
        }

        System.out.println("\n\nLivros atrasados");
        imprimeRelatorioUsuariosAtrasados();

        System.out.println("\n\nTodos os livros");
        imprimeColecaoDeLivros(biblioteca.retornaBiblioteca());

        System.out.println("top 10");
        imprimeTop10Usuarios(clientela.topUsuarios());


        System.out.println();
        menuPrincipal();
    }



}
