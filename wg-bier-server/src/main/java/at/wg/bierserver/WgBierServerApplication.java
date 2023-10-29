package at.wg.bierserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WgBierServerApplication {

	// using default port 8080
	public static void main(String[] args) {
		SpringApplication.run(WgBierServerApplication.class, args);
	}

}
