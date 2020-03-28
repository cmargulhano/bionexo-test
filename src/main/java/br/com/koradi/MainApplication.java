package br.com.koradi;

import br.com.koradi.repository.UbsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application Startercustomer
 *
 * @author Cláudio Margulhano
 */
@SpringBootApplication
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UbsRepository ubsRepository, ModelMapper modelMapper) {
    return args -> {

      // Load some data
      // In real app we could use liquibase

      /*
      Ubs ubs =
          new Ubs()
              .setFullName("Cláudio Margulhano")
              .setEmail("cmargulhano@gmail.com")
              .setBirthDate(of(1979, MAY, 15))
              .setGender(MALE)
              .setPhoneNumber("(12)99667-3166")
              .setMobileNumber("(12)99667-3166");
      customerRepository.save(ubs);
      */
    };
  }
}
