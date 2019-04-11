package com.cpsc471.tms.data.repository.schoolGrantApplications

import org.springframework.data.repository.CrudRepository

interface SchoolGrantApplicationsRepository : CrudRepository<SchoolGrantApplication, SchoolGrantApplicationKey> {
}