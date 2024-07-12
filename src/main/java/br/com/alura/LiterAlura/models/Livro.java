package br.com.alura.LiterAlura.models;

import br.com.alura.LiterAlura.dtos.LivroDTO;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private Double numeroDownloads;

    public Livro() {
    }

    public Livro(LivroDTO dto) {
        this.titulo = dto.titulo();
        this.autor = new Autor(dto.autor().get(0));
        this.idiomas = dto.idioma();
        this.numeroDownloads = dto.numeroDownloads();
    }

    public Livro(Long id, List<String> idiomas, Autor autor, Double numeroDownloads) {
        this.id = id;
        this.idiomas = idiomas;
        this.autor = autor;
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "----- LIVRO -----" + " \n" +
                "Título: " + titulo + " \n" +
                "Autor: " + (autor != null ? autor.getNome() : "Desconhecido") + " \n" +
                "Idiomas: " + String.join(", ", idiomas) + " \n" +
                "Downloads: " + numeroDownloads + " \n" +
                "-----------------";
    }
}
