package com.cpsc471.tms.data.repository.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, UserKey>{

    fun findByFirstNameStartsWithIgnoreCase(firstName : String) : List<User>

    fun findByUserKeyEmail(email :String) : User

}


