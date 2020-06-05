package com.example.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityJdbcDataSource {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcDataSource.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository,
                                 RoleRepository roleRepository) throws Exception{
        return (String[]args) -> {

            User admin = new User("rap", "rap", "rap9210@gmail.com", "Ronald", "Porrata", true);

            Role adminRole = new Role("rap", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);

            User user = new User("user", "user", "user@domain.com", "Mr", "User", true);

            Role userRole = new Role("user", "ROLE_USER");

            userRepository.save(user);
            roleRepository.save(userRole);
        };


    }


}
