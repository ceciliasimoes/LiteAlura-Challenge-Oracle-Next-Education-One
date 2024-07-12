package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.dtos.AutorDTO;
import br.com.alura.LiterAlura.dtos.LivroDTO;
import br.com.alura.LiterAlura.dtos.RootDTO;
import br.com.alura.LiterAlura.models.Autor;
import br.com.alura.LiterAlura.models.Livro;
import br.com.alura.LiterAlura.repositorys.IAutorRepository;
import br.com.alura.LiterAlura.repositorys.ILivroRepository;
import br.com.alura.LiterAlura.services.ConsumoApi;
import br.com.alura.LiterAlura.services.ConverteDados;
import br.com.alura.LiterAlura.services.IConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private IConverteDados converteDados = new ConverteDados();
    private static final String ENDERECO = "https://gutendex.com/books/?search=";

    @Autowired
    private ILivroRepository bibliotecaRepository;

    @Autowired
    private IAutorRepository autorRepository;

    public Main(ILivroRepository bibliotecaRepository, IAutorRepository autorRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() throws InterruptedException, JsonProcessingException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ****LiterALura****
                    - Sua Biblioteca-
                                    
                    1 - Buscar livro pelo Título
                    2 - Listar livros registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores vivos em algum ano
                    5 - Listar livros em um idioma específico
                    6 - Listar Autores por ano de nascimento
                    7 - Top 5 Livros
                    8 - Buscar Autor por nome
                                    
                    0 - Sair
                    """;
            System.out.println(menu);
            String input = this.scanner.nextLine();

            try {
                opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosBuscados();
                        break;
                    case 3:
                        listarAutoresBuscados();
                        break;
                    case 4:
                        listarAutoresVivosPorAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        listarAutoresPorAnoNascimento();
                        break;
                    case 7:
                        top5Livros();
                        break;
                    case 8:
                        buscarAutorPorNome();
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }

        }

    }


    private void buscarLivroPorTitulo() throws InterruptedException, JsonProcessingException {
        System.out.println("Insira o nome do livro que deseja procurar:");
        var nomeLivro = this.scanner.nextLine();
        var busca = nomeLivro.replace(" ", "%20");
        String url = ENDERECO + busca;

        var json = this.consumoApi.obterDados(url);

        if (json == null || json.isEmpty()) {
            System.out.println("Erro: Nenhum dado foi retornado pela API.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        RootDTO rootDTO = objectMapper.readValue(json, RootDTO.class);
        List<LivroDTO> resultados = rootDTO.results();
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("Erro: Nenhum resultado encontrado para o livro.");
        }

        LivroDTO livroDTO = resultados.get(0);
        Livro livro = new Livro(livroDTO);
        Autor autor = new Autor(livroDTO.autor().get(0));

        Optional<Livro> livroBuscado = this.bibliotecaRepository.findByTituloContaining(livro.getTitulo());
        Optional<Autor> autorBuscado = this.autorRepository.findByNomeContainingIgnoreCase(autor.getNome());

        if (autorBuscado.isPresent()) {
            var autorEncontrado = autorBuscado.get();
            autorEncontrado.addLivro(livro);
            autorRepository.save(autorEncontrado);
        } else {
            autor.addLivro(livro);
            autorRepository.save(autor);
        }

        if (livroBuscado.isPresent()) {
            System.out.println("Livro já cadastrado no Banco");
        } else {
            bibliotecaRepository.save(livro);
        }

        System.out.println(livro.toString());


    }

    private void listarLivrosBuscados() {
        List<Livro> livros = this.bibliotecaRepository.findAll();
        livros.stream().filter(livro -> livro.getTitulo() != null).sorted(Comparator.comparing(Livro::getTitulo)).forEach(System.out::println);
    }


    private void listarAutoresBuscados() {
        List<Autor> autores = this.autorRepository.findAll();
        autores.stream().filter(autor -> autor.getNome() != null).sorted(Comparator.comparing(Autor::getNome)).forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano que deseja ver os autores que estavam vivos:");
        var anoBuscado = this.scanner.nextInt();
        scanner.nextLine();
        List<Autor> autores = this.autorRepository.findAutoresVivosNoAno(anoBuscado);
        autores.stream().filter(autor -> autor.getNome() != null).sorted(Comparator.comparing(Autor::getNome)).forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma que deseja buscar:");
        System.out.println("- en :inglês " + "\n" + "- es : espanhol " + "\n" + "- fr : francês " + "\n" + "- pt : português");
        var idioma = this.scanner.nextLine();
        var livros = this.bibliotecaRepository.findByIdiomasContainingIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro no idioma ' " + idioma + " ' foi encontrado ");
        } else {
            System.out.println("Os livros encontrados no idioma " + idioma + " foram :");
            livros.stream().filter(livro -> livro.getTitulo() != null).sorted(Comparator.comparing(Livro::getTitulo)).forEach(System.out::println);
        }
    }

    private void listarAutoresPorAnoNascimento() {
        System.out.println("Insira o ano que deseja ver os autores que nasceram nele:");
        var ano = this.scanner.nextInt();
        scanner.nextLine();
        var autores = this.autorRepository.findByAnoNascimentoEquals(ano);
        if (autores.isEmpty()) {
            System.out.println("Não foi encontrado nehum autor nascido no ano de " + ano);
        } else {
            System.out.println("O(s) autor(es) nascido(s) no ano de " + ano + " foi(ram): ");
            autores.stream().filter(autor -> autor.getNome() != null).sorted(Comparator.comparing(Autor::getNome)).forEach(System.out::println);
        }
    }


    private void top5Livros() {
        System.out.println("Os Top 5 livros mais baixados são: ");
        var livros = this.bibliotecaRepository.findTop5ByOrderByNumeroDownloadsDesc();
        livros.stream().filter(livro -> livro.getTitulo() != null).forEach(System.out::println);
    }

    private void buscarAutorPorNome() {
        System.out.println("Insira o nome do autor que deseja buscar: ");
        var nome = this.scanner.nextLine();
        var autor = this.autorRepository.findByNomeContainingIgnoreCase(nome);
        if (autor.isEmpty()) {
            System.out.println("O autor de nome " + nome + " não foi encontrado!");
        } else {
            System.out.println(autor);
        }
    }


}
