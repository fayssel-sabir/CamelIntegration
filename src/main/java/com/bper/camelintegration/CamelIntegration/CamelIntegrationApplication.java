package com.bper.camelintegration.CamelIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:camel-context.xml"})
public class CamelIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelIntegrationApplication.class, args);
	}

}
