package br.upe.is;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AutenticacaoTechCycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticacaoTechCycleApplication.class, args);
	}

}
