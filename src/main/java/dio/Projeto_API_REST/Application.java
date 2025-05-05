package dio.Projeto_API_REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL")})
@EntityScan("dio.Projeto_API_REST.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}	
}
