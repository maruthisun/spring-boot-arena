package com.app.paintCalculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Paint Calculator API", version = "1.0", description = "Estimate your Paint needs"))

@ComponentScan(basePackages = "com.app.paintCalculator.*")
public class PaintCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaintCalculatorApplication.class, args);
	}

}
