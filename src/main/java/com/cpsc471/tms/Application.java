package com.cpsc471.tms;

import com.cpsc471.tms.data.repos.ArtistRepository;
import com.cpsc471.tms.data.types.Artist;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(ArtistRepository repo){
        return (args -> {
            Artist n = new Artist(Collections.emptyList(),Collections.emptySet(),Collections.emptyList());
            n.setEmail("devon@hockley.ca");
            n.setFirstName("Devon");
            n.setLastName("Hockley");
            n.setPostalCode("T2K0P1");
            repo.save(n);


        });
    }

}

