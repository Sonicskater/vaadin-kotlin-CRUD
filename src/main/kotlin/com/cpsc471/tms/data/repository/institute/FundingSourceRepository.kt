package com.cpsc471.tms.data.repository.institute

import org.springframework.data.repository.CrudRepository

interface FundingSourceRepository : CrudRepository<FundingSource, InstituteKey> {
}