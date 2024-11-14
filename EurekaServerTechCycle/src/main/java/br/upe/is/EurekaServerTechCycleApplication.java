package br.upe.is;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerTechCycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerTechCycleApplication.class, args);
	}

}
