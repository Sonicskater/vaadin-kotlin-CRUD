package com.cpsc471.tms;

import com.cpsc471.tms.data.repos.ArtistRepository;
import com.cpsc471.tms.data.repos.ProjectRepository;
import com.cpsc471.tms.data.repos.SchoolRepository;
import com.cpsc471.tms.data.types.Artist;
import com.cpsc471.tms.data.types.School;
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
    public CommandLineRunner loadData(
            ArtistRepository artistRepository,
            SchoolRepository schoolRepository,
            ProjectRepository projectRepository){
        return (args -> {
            Artist n = new Artist(Collections.emptyList(),Collections.emptySet(),Collections.emptyList());
            n.getUserKey().setEmail("devon@hockley.ca");
            n.setFirstName("Devon");
            n.setLastName("Hockley");
            n.setPostalCode("T2K0P1");
            artistRepository.save(n);

            School s = new School();

            s.setGradeMax(9);
            s.setGradeMin(7);
            s.getInstituteKey().setPostalCode("T3L 2E6");
            s.getInstituteKey().setName("St Jean Brebeuf");
            schoolRepository.save(s);

            RepoHelper.artistRepository = artistRepository;
            RepoHelper.schoolRepository = schoolRepository;
            RepoHelper.projectRepository = projectRepository;

        });
    }


}

