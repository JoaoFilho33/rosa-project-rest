package com.rosa.rosaRest;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Product", version = "1", description = "API para testes do OpenApi"))
public class RosaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosaRestApplication.class, args);
	}

}
