package com.cpsc471.tms.data.repository.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.sql.Date

@Repository
interface ArtistRepository : CrudRepository<Artist, String> {

    fun findArtistsByAvailableDaysContains(dates : Set<Date>)

    fun findArtistsByFirstNameContainsOrLastNameContains(firstName: String, lastName : String) : List<Artist>
}