package br.com.alura.LiterAlura.repositorys;

import br.com.alura.LiterAlura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoFalecimento >= :anoBuscado AND a.anoNascimento IS NOT NULL AND a.anoNascimento <= :anoBuscado")
    List<Autor> findAutoresVivosNoAno(Integer anoBuscado);

    List<Autor> findByAnoNascimentoEquals(int ano);
}
