package br.com.alura.LiterAlura.dtos;
//livro: titulo,autor,idioma e numero de downloads

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<AutorDTO> autor,

        @JsonAlias("languages") List<String> idioma,

        @JsonAlias("download_count") Double numeroDownloads
) {
}
