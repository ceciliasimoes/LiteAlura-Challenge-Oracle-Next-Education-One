package br.com.alura.LiterAlura.repositorys;

import br.com.alura.LiterAlura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILivroRepository extends JpaRepository<Livro,Long> {
    Optional<Livro> findByTituloContaining(String titulo);

    List<Livro> findByIdiomasContainingIgnoreCase(String idioma);

    List<Livro> findTop5ByOrderByNumeroDownloadsDesc();
}
