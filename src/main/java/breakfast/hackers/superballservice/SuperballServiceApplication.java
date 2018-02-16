package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

@SpringBootApplication
public class SuperballServiceApplication {
    
    @Autowired
    private SuperballSpeechlet superballSpeechlet;

	public static void main(String[] args) {
		SpringApplication.run(SuperballServiceApplication.class, args);
	}
	
	@Bean
    public ServletRegistrationBean registerServlet() {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(superballSpeechlet);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(speechletServlet, "/api/speechlet/movements");
        return servletRegistrationBean;     
    }
}
