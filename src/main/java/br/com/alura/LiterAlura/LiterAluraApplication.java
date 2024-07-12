package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.principal.Main;
import br.com.alura.LiterAlura.repositorys.IAutorRepository;
import br.com.alura.LiterAlura.repositorys.ILivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private ILivroRepository bibliotecaRepository;

	@Autowired
	private IAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(this.bibliotecaRepository, this.autorRepository);
		main.exibeMenu();
	}
}
