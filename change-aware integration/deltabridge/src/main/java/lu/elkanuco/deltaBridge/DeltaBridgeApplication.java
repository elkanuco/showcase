package lu.elkanuco.deltaBridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "lu.elkanuco.deltaBridge.feign")
@SpringBootApplication
public class DeltaBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeltaBridgeApplication.class, args);
	}

}
