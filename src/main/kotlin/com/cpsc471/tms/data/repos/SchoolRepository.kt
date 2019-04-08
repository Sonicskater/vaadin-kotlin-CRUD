package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.data.types.School
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.reflect.Type

@Repository
interface SchoolRepository : CrudRepository<School, InstituteKey>{
}
