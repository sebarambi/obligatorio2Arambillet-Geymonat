package com.example.obligatorioDDA;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ObligatorioDdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioDdaApplication.class, args);
	}

	//Implementamoos Springdoc OpenAPI para documentar la API
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Bienvenidos al obligatorio de Sebastian Arambillet y Rafael Geymonat")
						.version("0.11")
						.description("Proyecto de obligatorio de la materia Diseño y Desarrollo de Aplicaciones")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("https://springdoc.org"))
				);
	}
	//http://localhost:5001/swagger-ui/index.html#/ para ver la documentacion de la API
}
