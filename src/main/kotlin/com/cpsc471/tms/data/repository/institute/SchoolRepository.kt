package com.cpsc471.tms.data.repository.institute

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository : CrudRepository<School, InstituteKey>
