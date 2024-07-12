package br.com.alura.LiterAlura.models;

import br.com.alura.LiterAlura.dtos.AutorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(AutorDTO dto) {
        this.nome = dto.nome();
        this.anoNascimento = dto.anoNascimento();
        this.anoFalecimento = dto.anoFalecimento();
    }

    public Autor() {
    }

    public Autor(Long id, String nome, Integer anoNascimento, Integer anoFalecimento, List<Livro> livros) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
        livro.setAutor(this);
    }

    @Override
    public String toString() {
        return "----- Autor -----  \n" +
                "Autor: " + nome + " \n" +
                "Data de Nascimento: " + anoNascimento + " \n" +
                "Data de Falecimento: " + anoFalecimento + " \n" +
                "Livros :" + livros.stream().map(Livro::getTitulo).toList() +" \n" +
                "-----------------"
                ;
    }
}

