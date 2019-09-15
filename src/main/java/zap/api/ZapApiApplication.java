package zap.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zap.api.model.Building;

@SpringBootApplication
public class ZapApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZapApiApplication.class, args);		
	}

}
