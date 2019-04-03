package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.types.Artist
import com.cpsc471.tms.data.types.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.sql.Date

@Repository
interface UserRepository : CrudRepository<User, String>{

    fun findByFirstNameStartsWithIgnoreCase(firstName : String) : List<User>

    fun findByEmail(email :String) : User

}


@Repository
interface ArtistRepository : CrudRepository<Artist, String>{

    fun findArtistsByAvailableDaysContains(dates : Set<Date>)

    fun findArtistsByFirstNameContainsOrLastNameContains(firstName: String, lastName : String) : List<Artist>
}

@Repository
interface ManagerRepository : CrudRepository<User, String>{



}