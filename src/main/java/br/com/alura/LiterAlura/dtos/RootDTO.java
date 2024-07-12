package br.com.alura.LiterAlura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RootDTO(
        List<LivroDTO> results
) {
}
