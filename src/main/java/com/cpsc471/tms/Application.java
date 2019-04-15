package com.cpsc471.tms;

import com.cpsc471.tms.data.repository.contactContactInfos.ContactContactInfoRepository;
import com.cpsc471.tms.data.repository.contacts.ContactRepository;
import com.cpsc471.tms.data.repository.institute.FundingSourceRepository;
import com.cpsc471.tms.data.repository.institute.InstituteRepository;
import com.cpsc471.tms.data.repository.institute.SchoolRepository;
import com.cpsc471.tms.data.repository.invoiceItems.InvoiceItemRepository;
import com.cpsc471.tms.data.repository.invoices.InvoiceRepository;
import com.cpsc471.tms.data.repository.projects.ProjectRepository;
import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplicationsRepository;
import com.cpsc471.tms.data.repository.selfGrants.SelfGrantRepository;
import com.cpsc471.tms.data.repository.userContactInfos.UserContactInfoRepository;
import com.cpsc471.tms.data.repository.users.ArtistRepository;
import com.cpsc471.tms.data.repository.users.ManagerRepository;
import com.cpsc471.tms.data.repository.users.UserRepository;
import com.cpsc471.tms.data.repository.vehicles.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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
            ManagerRepository managerRepository,
            UserRepository userRepository,
            UserContactInfoRepository userContactInfoRepository,

            ProjectRepository projectRepository,

            InstituteRepository instituteRepository,
            SchoolRepository schoolRepository,
            FundingSourceRepository fundingSourceRepository,

            VehicleRepository vehicleRepository,


            ContactRepository contactRepository,
            ContactContactInfoRepository contactContactInfoRepository,

            SelfGrantRepository selfGrantRepository,
            SchoolGrantApplicationsRepository schoolGrantApplicationsRepository,

            InvoiceItemRepository invoiceItemRepository,
            InvoiceRepository invoiceRepository
            ){
        return (args -> {

            RepoHelper.managerRepository = managerRepository;
            RepoHelper.artistRepository = artistRepository;
            RepoHelper.userRepository = userRepository;
            RepoHelper.userContactInfoRepository = userContactInfoRepository;

            RepoHelper.invoiceRepository = invoiceRepository;
            RepoHelper.invoiceItemRepository = invoiceItemRepository;

            RepoHelper.schoolRepository = schoolRepository;
            RepoHelper.fundingSourceRepository = fundingSourceRepository;
            RepoHelper.instituteRepository = instituteRepository;

            RepoHelper.vehicleRepository = vehicleRepository;

            RepoHelper.projectRepository = projectRepository;

            RepoHelper.contactRepository = contactRepository;
            RepoHelper.contactContactInfoRepository = contactContactInfoRepository;

            RepoHelper.selfGrantRepository = selfGrantRepository;
            RepoHelper.schoolGrantApplicationsRepository = schoolGrantApplicationsRepository;


        });
    }


}

