package telran.microservices;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscoveryLoadbalancerClientApplication {
	@Autowired
LoadBalancer loadBalancer;
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryLoadbalancerClientApplication.class, args);
	}
	
	@PostConstruct
	void displayServicesUri() {
		while(true) {
			System.out.println(loadBalancer.getBaseUrl("register-client"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
