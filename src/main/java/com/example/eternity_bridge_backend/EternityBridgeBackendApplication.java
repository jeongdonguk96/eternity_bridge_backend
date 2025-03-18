package com.example.eternity_bridge_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EternityBridgeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EternityBridgeBackendApplication.class, args);
	}

}
