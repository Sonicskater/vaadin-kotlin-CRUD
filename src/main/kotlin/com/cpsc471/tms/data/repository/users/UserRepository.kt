package com.cpsc471.tms.data.repository.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, String>{

    fun findByFirstNameStartsWithIgnoreCase(firstName : String) : List<User>

    fun findByUserKeyEmail(email :String) : User

}


