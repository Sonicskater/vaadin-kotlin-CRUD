package com.cpsc471.tms.data.repository.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerRepository : CrudRepository<Manager, UserKey>{

    fun findByUserKeyEmail(string: String) : Manager
}