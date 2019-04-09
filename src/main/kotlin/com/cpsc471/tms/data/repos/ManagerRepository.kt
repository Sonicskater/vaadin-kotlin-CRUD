package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.types.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerRepository : CrudRepository<User, String>