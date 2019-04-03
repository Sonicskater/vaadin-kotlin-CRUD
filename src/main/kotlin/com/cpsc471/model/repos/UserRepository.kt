package com.cpsc471.model.repos

import com.cpsc471.model.types.Artist
import com.cpsc471.model.types.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Repository
interface UserRepository : CrudRepository<User, String>{

    fun findByFirstNameStartsWithIgnoreCase(firstName : String) : List<User>

}


@Repository
interface ArtistRepository : CrudRepository<Artist, String>{

    fun findArtistsByAvailableDaysContains(dates : Set<Date>)


    fun findArtistsByFirstNameContainsOrLastNameContains(firstName: String, lastName : String) : List<Artist>
}