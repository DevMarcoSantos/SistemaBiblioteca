void main() {

    Scanner sc = new Scanner(System.in);

    String senha = "biblio1234";
    double multaPorDia = 2;
    int escolha = 0;

    //CADASTRO LIVROS
    List<String> titulos = new ArrayList<>();
    titulos.add("Dom Casmurro");
    titulos.add("1984");
    titulos.add("Memorias postumas de bras cubas");
    titulos.add("Grande Sertao Veredas");

    List<String> autores = new ArrayList<>();
    autores.add("Machado de Assis");
    autores.add("George Orwell");
    autores.add("Machado de Assis");
    autores.add("Joao Guimaraes Rosa");

    List<Integer> exemplaresDisponiveis = new ArrayList<>();
    exemplaresDisponiveis.add(3);
    exemplaresDisponiveis.add(2);
    exemplaresDisponiveis.add(4);
    exemplaresDisponiveis.add(2);

    //CADASTRO USUARIOS
    List<String> nomeMembros = new ArrayList<>();
    nomeMembros.add("Ricardo");
    nomeMembros.add("Maria");
    nomeMembros.add("Claudia");
    nomeMembros.add("Calebe");

    List<String> tipoMembros = new ArrayList<>();
    tipoMembros.add("funcionario");
    tipoMembros.add("professor");
    tipoMembros.add("aluno");
    tipoMembros.add("aluno");

    List<Integer> matriculaMembros = new ArrayList<>();
    matriculaMembros.add(2026201482);
    matriculaMembros.add(2026203915);
    matriculaMembros.add(2026207409);
    matriculaMembros.add(2026208631);

    List<Integer> LivrosEmprestados = new ArrayList<>();
    LivrosEmprestados.add(1);
    LivrosEmprestados.add(2);
    LivrosEmprestados.add(3);
    LivrosEmprestados.add(2);

    //REGISTRO RESERVAS
    List<String> tituloLivroReserva = new ArrayList<>();
    List<String> usuarioReserva = new ArrayList<>();

    //REGISTRO EMPRESTIMOS
    List<String> tituloLivroEmprestado = new ArrayList<>();
    List<String> usuarioEmprestado = new ArrayList<>();

    //MENU
    do {
        String tentativa_senha = "";

        System.out.println("SISTEMA DE GESTÃO BIBLIOTECA 1.0.0");
        System.out.print("Informe seu nível de acesso (Administrador - 1 / usuario - 2 / SAIR - 3): ");
        escolha = sc.nextInt();
        sc.nextLine();   // limpa o buffer do scanner - evita bug de duplicar a mensagem

        if (escolha == 1) {
            int tentativas = 0;
            while (tentativas < 3) {
                int tentativas_restantes = 2 - tentativas;
                System.out.print("Informe a senha de acesso para Administrador ou digite SAIR para voltar ao menu: ");
                tentativa_senha = sc.nextLine();

                if (Objects.equals(tentativa_senha, senha)) {
                    int opcao_adm = 0;
                    while (opcao_adm != 7) {
                        System.out.println("Você logou como ADMINISTRADOR");
                        System.out.println("Selecione uma opção: ");
                        System.out.println("1- Consultar acervo");
                        System.out.println("2- Realizar emprestimo");
                        System.out.println("3- Realizar devolução");
                        System.out.println("4- Aplicar multa");
                        System.out.println("5- Cadastrar Livro");
                        System.out.println("6- Cadastrar usuario");
                        System.out.println("7- Logout");
                        opcao_adm = sc.nextInt();
                        sc.nextLine();

                        switch (opcao_adm) {
                            case 1:
                                System.out.println("Você escolheu - Consultar acervo");
                                System.out.println("-------------------------------------------");
                                System.out.print("Digite o título do livro a ser buscado: ");
                                String busca = sc.nextLine();

                                if (titulos.contains(busca)) {
                                    int indice = titulos.indexOf(busca);
                                    String titulo = titulos.get(indice);
                                    String autor = autores.get(indice);
                                    int qtdExemplar = exemplaresDisponiveis.get(indice);
                                    System.out.println("-------------------------------------------");
                                    System.out.println("*** RESULTADO *** ");
                                    System.out.println("Título: " + titulo);
                                    System.out.println("Autor: " + autor);
                                    System.out.println("Quantidade de exemplares disponíveis: " + qtdExemplar);
                                    System.out.println("-------------------------------------------");
                                } else {
                                    System.out.println("O livro " + busca + " não está disponível em nossa biblioteca");
                                }
                                break;
                            case 2:
                                System.out.println("Você escolheu - Realizar empréstimo");
                                System.out.println("------------------------------------");
                                System.out.print("Informe o título do livro a ser emprestado: ");
                                String livroEmprestado = sc.nextLine();

                                if (titulos.contains(livroEmprestado)) {
                                    int indice = titulos.indexOf(livroEmprestado);
                                    int qtdExemplaresDisponiveis = exemplaresDisponiveis.get(indice);

                                    if (qtdExemplaresDisponiveis > 0) {
                                        System.out.print("Informe o nome do usuário que o livro será emprestado: ");
                                        String usuario = sc.nextLine();

                                        if (nomeMembros.contains(usuario)) {
                                            int indiceMembro = nomeMembros.indexOf(usuario);
                                            int qtdLivrosEmprestados = LivrosEmprestados.get(indiceMembro);

                                            if (qtdLivrosEmprestados >= 3) {
                                                System.out.println("O usuário " + usuario + " excedeu o limite de livros emprestados.\n");
                                            } else {
                                                LivrosEmprestados.set(indiceMembro, qtdLivrosEmprestados + 1);
                                                exemplaresDisponiveis.set(indice, qtdExemplaresDisponiveis - 1);
                                                usuarioEmprestado.add(usuario);
                                                tituloLivroEmprestado.add(livroEmprestado);

                                                System.out.println("O empréstimo foi realizado com sucesso!");
                                                System.out.println("---- DADOS DO EMPRÉSTIMO ----");
                                                System.out.println("Livro emprestado: " + livroEmprestado);
                                                System.out.println("Usuário solicitante: " + usuario);
                                                System.out.println("PRAZO MÁXIMO PARA DEVOLUÇÃO: 14 DIAS");
                                                System.out.println("------------------------------------\n");
                                            }
                                        } else {
                                            System.out.println("Nome de usuário inválido.\n");
                                        }
                                    } else {
                                        System.out.println("Não existem exemplares disponíveis para o título informado.");
                                        System.out.print("Deseja fazer reserva? (S/N): ");
                                        String opcaoReserva = sc.nextLine().toLowerCase();

                                        if (opcaoReserva.equals("s")) {
                                            System.out.print("Informe o nome do usuário que quer fazer a reserva: ");
                                            String usuario = sc.nextLine();

                                            if (nomeMembros.contains(usuario)) {
                                                tituloLivroReserva.add(livroEmprestado);
                                                usuarioReserva.add(usuario);

                                                System.out.println("Reserva realizada com sucesso!");
                                                System.out.println("Usuário que reservou: " + usuario);
                                                System.out.println("Título reservado: " + livroEmprestado);
                                                System.out.println("------------------------------------\n");
                                            } else {
                                                System.out.println("Usuário não cadastrado. Não foi possível realizar a reserva.\n");
                                            }
                                        } else {
                                            System.out.println("Você optou por não fazer reserva.");
                                            System.out.println("------------------------------------\n");
                                        }
                                    }
                                } else {
                                    System.out.println("Título não encontrado no acervo.\n");
                                }
                                break;
                            case 3:

                                System.out.println("Você escolheu - Realizar devolução");
                                System.out.println("-------------------------------------------");
                                System.out.print("Informe o nome do livro a ser devolvido: ");
                                String livroDevolucao = sc.nextLine();
                                System.out.print("Informe o nome do usuario que quer devolver: ");
                                String usuarioDevolucao = sc.nextLine();

                                int indiceEmprestimo = -1;  // bate o indice do livro com o indice do usuario para nao excluir errado o registro do emprestimo
                                for (int i = 0; i < usuarioEmprestado.size(); i++) {
                                    if (usuarioEmprestado.get(i).equals(usuarioDevolucao)
                                            && tituloLivroEmprestado.get(i).equals(livroDevolucao)) {
                                        indiceEmprestimo = i;
                                        break;
                                    }
                                }

                                if (indiceEmprestimo != -1) {
                                    tituloLivroEmprestado.remove(indiceEmprestimo);
                                    usuarioEmprestado.remove(indiceEmprestimo);

                                    int indiceUsuario = nomeMembros.indexOf(usuarioDevolucao);
                                    LivrosEmprestados.set(indiceUsuario, LivrosEmprestados.get(indiceUsuario) - 1);

                                    int indiceLivro = titulos.indexOf(livroDevolucao);
                                    exemplaresDisponiveis.set(indiceLivro, exemplaresDisponiveis.get(indiceLivro) + 1);

                                    System.out.println("Devolução feita com sucesso!");
                                    System.out.println("NOTA AO BIBLIOTECÁRIO: Verifique o prazo de devolução e aplique a multa se necessário.");
                                    System.out.println("------------------------------------\n");
                                } else {
                                    System.out.println("Nenhum empréstimo ativo encontrado para este usuário e livro.\n");
                                }
                                break;
                            case 4:
                                System.out.println("Você escolheu - Aplicar multa");
                                System.out.println("-------------------------------------------");
                                System.out.print("Informe o nome do usuario: ");
                                String usuario = sc.nextLine();
                                String usuarioMulta = sc.nextLine();

                                if (nomeMembros.contains(usuarioMulta)) {
                                    System.out.print("Informe a quantidade de dias atrasados até a devolução: ");
                                    int diasAtrasados = sc.nextInt();
                                    sc.nextLine();

                                    double multa = diasAtrasados * multaPorDia;

                                    System.out.println("Multa aplicada!");
                                    System.out.println("Usuário penalizado: " + usuarioMulta);
                                    System.out.println("Valor da multa: R$ " + multa);
                                    System.out.println("------------------------------------\n");
                                } else {
                                    System.out.println("Usuário não encontrado no cadastro.\n");
                                }
                                break;
                            case 5:
                                System.out.println("Você escolheu - Cadastrar Livro");
                                System.out.println("-------------------------------------------");
                                System.out.print("Informe o título do livro para cadastro: ");
                                String titulo = sc.nextLine();

                                if (!titulos.contains(titulo)) {
                                    System.out.print("Informe o autor do livro para cadastro: ");
                                    String autor = sc.nextLine();
                                    System.out.print("Informe a quantidade de exemplares do livro para cadastro: ");
                                    int qtdExemplares = sc.nextInt();
                                    sc.nextLine();

                                    titulos.add(titulo);
                                    autores.add(autor);
                                    exemplaresDisponiveis.add(qtdExemplares);

                                    System.out.println("Livro adicionado com sucesso!");
                                    System.out.println("Título: " + titulo);
                                    System.out.println("Autor: " + autor);
                                    System.out.println("Quantidade de exemplares disponíveis: " + qtdExemplares);
                                    System.out.println("-------------------------------------------\n");
                                } else {
                                    System.out.print("o titulo informado já está cadastrado, deseja adicionar exemplares? (S/N)");
                                    String escolhaRegistro = sc.nextLine().toLowerCase();
                                    if (escolhaRegistro.equals("s")) {
                                        System.out.println("Quantos exemplares deseja cadastrar: ");
                                        int qtdExemplares = sc.nextInt();
                                        sc.nextLine();
                                        int indiceLivro = titulos.indexOf(titulo);
                                        exemplaresDisponiveis.set(indiceLivro, exemplaresDisponiveis.get(indiceLivro) + qtdExemplares);

                                        System.out.println(qtdExemplares + " exemplar(es) adicionado(s) para o livro " + titulo + ", nova quantidade de exemplares disponiveis: " + exemplaresDisponiveis.get(indiceLivro));
                                        System.out.println("-------------------------------------------\n");
                                    } else {
                                        System.out.println("Você escolheu não cadastrar exemplares.");
                                    }
                                }
                                break;
                            case 6:
                                System.out.println("Você escolheu - Cadastrar Usuario");
                                System.out.print("Informe a matricula do usuario para cadastro: ");
                                int matricula = sc.nextInt();
                                sc.nextLine();

                                if (matriculaMembros.contains(matricula)) {
                                    int indiceUsuario = matriculaMembros.indexOf(matricula);
                                    System.out.println("O usuario " + nomeMembros.get(indiceUsuario) + " já está cadastrado");
                                } else {
                                    System.out.print("Informe o nome do usuario para cadastro: ");
                                    String nome = sc.nextLine();
                                    System.out.print("Informe se o usuario é aluno, professor ou funcionario: ");
                                    String tipo = sc.nextLine();

                                    nomeMembros.add(nome);
                                    tipoMembros.add(tipo);
                                    matriculaMembros.add(matricula);
                                    LivrosEmprestados.add(0);

                                    System.out.println("-------------------------------------------");
                                    System.out.println("NOVO MEMBRO CADASTRADO ");
                                    System.out.println("Nome: " + nome);
                                    System.out.println("Matricula: " + matricula);
                                    System.out.println("Tipo: " + tipo);
                                    System.out.println("-------------------------------------------\n");
                                }
                                break;
                            case 7:
                                System.out.println("Fazendo logout");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    }
                } else if (tentativa_senha.equals("SAIR")) {
                    break;
                } else {
                    System.out.println("Senha INVÁLIDA - " + tentativas_restantes + " tentativas restantes ");
                    tentativas++;
                }
            }
            ;
        } else if (escolha == 2) {
            while (true) {
                System.out.println("Você logou como USUÁRIO");
                System.out.println("Selecione uma opção: ");
                System.out.println("1- Consultar acervo");
                System.out.println("2- Logout");
                int opcaoUsuario = sc.nextInt();
                sc.nextLine();

                if (opcaoUsuario == 1) {
                    System.out.println("Você escolheu - Consultar acervo");
                    System.out.println("------------------------------------");
                    System.out.print("Digite o título do livro a ser buscado: ");
                    String busca = sc.nextLine();

                    if (titulos.contains(busca)) {
                        int indice = titulos.indexOf(busca);
                        String titulo = titulos.get(indice);
                        String autor = autores.get(indice);
                        int qtdExemplar = exemplaresDisponiveis.get(indice);

                        System.out.println("------------------------------------");
                        System.out.println("*** RESULTADO ***");
                        System.out.println("Título: " + titulo);
                        System.out.println("Autor: " + autor);
                        System.out.println("Quantidade de exemplares disponíveis: " + qtdExemplar);
                        System.out.println("------------------------------------");

                        if (qtdExemplar > 0) {
                            System.out.println("Livro está disponível, solicite ao bibliotecário um exemplar.\n");
                        } else {
                            System.out.println("Não existem exemplares disponíveis, solicite ao bibliotecário para fazer a reserva.\n");
                        }
                    } else {
                        System.out.println("O livro " + busca + " não está disponível em nossa biblioteca.\n");
                    }
                } else if (opcaoUsuario == 2) {
                    break;
                } else {
                    System.out.println("Opção inválida!\n");
                }
            }
        } else if (escolha == 3) {
            break;
        } else {
            System.out.println("Escolha inválida.\n");
        }
    } while (true);

    System.out.println("PROGRAMA ENCERRADO");
}