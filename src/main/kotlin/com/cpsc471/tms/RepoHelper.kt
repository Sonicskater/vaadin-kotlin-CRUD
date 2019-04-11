package com.cpsc471.tms

import com.cpsc471.tms.data.repository.contactContactInfos.ContactContactInfoRepository
import com.cpsc471.tms.data.repository.contacts.ContactRepository
import com.cpsc471.tms.data.repository.institute.FundingSourceRepository
import com.cpsc471.tms.data.repository.institute.InstituteRepository
import com.cpsc471.tms.data.repository.institute.SchoolRepository
import com.cpsc471.tms.data.repository.invoiceItems.InvoiceItemRepository
import com.cpsc471.tms.data.repository.invoices.InvoiceRepository
import com.cpsc471.tms.data.repository.projects.ProjectRepository
import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplicationsRepository
import com.cpsc471.tms.data.repository.selfGrants.SelfGrantRepository
import com.cpsc471.tms.data.repository.userContactInfos.UserContactInfoRepository
import com.cpsc471.tms.data.repository.users.ArtistRepository
import com.cpsc471.tms.data.repository.users.ManagerRepository
import com.cpsc471.tms.data.repository.users.UserRepository
import com.cpsc471.tms.data.repository.vehicles.VehicleRepository



object RepoHelper{

    lateinit var schoolRepository: SchoolRepository
    lateinit var instituteRepository: InstituteRepository
    lateinit var fundingSourceRepository: FundingSourceRepository

    lateinit var projectRepository: ProjectRepository

    lateinit var artistRepository: ArtistRepository
    lateinit var managerRepository: ManagerRepository
    lateinit var userRepository: UserRepository
    lateinit var userContactInfoRepository: UserContactInfoRepository

    lateinit var contactRepository: ContactRepository
    lateinit var contactContactInfoRepository: ContactContactInfoRepository

    lateinit var vehicleRepository: VehicleRepository

    lateinit var invoiceRepository: InvoiceRepository
    lateinit var invoiceItemRepository: InvoiceItemRepository

    lateinit var selfGrantRepository: SelfGrantRepository
    lateinit var schoolGrantApplicationsRepository: SchoolGrantApplicationsRepository


}
