package api.Tienda;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"api.Controller", "api.Services", "api.Repository", "api.Daos", "api.Config"})
@EnableJpaRepositories(basePackages = "api.Repository")
@EntityScan(basePackages = "api.Daos")
/**
 * Clase principal de la aplicacion
 */
public class apiTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(apiTiendaApplication.class, args);

	
	}

}
