package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.types.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, String>{

    fun findByFirstNameStartsWithIgnoreCase(firstName : String) : List<User>

    fun findByEmail(email :String) : User

}


