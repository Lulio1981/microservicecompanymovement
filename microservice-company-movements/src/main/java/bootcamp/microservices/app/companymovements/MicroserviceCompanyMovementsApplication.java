package bootcamp.microservices.app.companymovements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroserviceCompanyMovementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCompanyMovementsApplication.class, args);
	}

}
