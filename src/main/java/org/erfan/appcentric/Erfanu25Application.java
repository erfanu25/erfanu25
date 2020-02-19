package org.erfan.appcentric;

import org.erfan.appcentric.entity.CustomerEntity;
import org.erfan.appcentric.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Erfanu25Application {

	public static void main(String[] args) {
		SpringApplication.run(Erfanu25Application.class, args);
        browse();
	}

    private static void browse() {
        String url = "http://localhost:8083/swagger-ui.html";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


	@Bean
	public CommandLineRunner demoData(CustomerRepository customerRepository) {
		return args -> {
			CustomerEntity customerEntity = new CustomerEntity();
			if (customerRepository.findByFirstName("Erfan").isEmpty()) {
                customerEntity.setFirstName("Erfan");
                customerEntity.setLastName("Bhuiyan");
                customerEntity.setEmail("mderfan2@gmail.com");
                customerRepository.save(customerEntity);
            }
		};
	}

}