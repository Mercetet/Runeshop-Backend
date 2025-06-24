package com.example.runeshop_ecommerce.utils;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class DotenvLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		Dotenv dotenv = Dotenv.configure().load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
			System.out.println("[DotenvInitializer] Variable cargada: " + entry.getKey());
		});
	}
}


