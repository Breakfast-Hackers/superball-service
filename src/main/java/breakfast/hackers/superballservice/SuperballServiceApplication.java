package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperballServiceApplication {
    
    @Autowired
    private SuperballSpeechlet superballSpeechlet;

	public static void main(String[] args) {
		SpringApplication.run(SuperballServiceApplication.class, args);
	}
	
}
