package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.data.types.Institute
import org.springframework.data.repository.CrudRepository

interface InstituteRepository : CrudRepository<Institute, InstituteKey>{

}