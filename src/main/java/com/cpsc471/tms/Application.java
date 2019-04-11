package com.cpsc471.tms;

import com.cpsc471.tms.data.repository.institute.InstituteRepository;
import com.cpsc471.tms.data.repository.institute.SchoolRepository;
import com.cpsc471.tms.data.repository.projects.ProjectRepository;
import com.cpsc471.tms.data.repository.users.ArtistRepository;
import com.cpsc471.tms.data.repository.vehicles.VehicleRepository;
import com.cpsc471.tms.data.repository.users.Artist;
import com.cpsc471.tms.data.repository.projects.Project;
import com.cpsc471.tms.data.repository.institute.School;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
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
            ProjectRepository projectRepository,
            InstituteRepository instituteRepository,
            VehicleRepository vehiclieRepository){
        return (args -> {
            Artist n = new Artist();
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

            System.out.println(LocalDate.of(1900, 1, 1).equals(LocalDate.of(1900, 1, 1)));
            System.out.println(LocalDate.of(1900, 1, 1)==(LocalDate.of(1900, 1, 1)));

            System.out.println(new Project().equals(new Project()));
            System.out.println(new Project() == (new Project()));

            RepoHelper.artistRepository = artistRepository;
            RepoHelper.schoolRepository = schoolRepository;
            RepoHelper.projectRepository = projectRepository;
            RepoHelper.instituteRepository = instituteRepository;
            RepoHelper.vehicleRepository = vehiclieRepository;

        });
    }


}

