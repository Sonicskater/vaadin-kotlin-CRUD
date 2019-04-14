package com.cpsc471.tms.data.repository.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistRepository : CrudRepository<Artist, UserKey> {

    fun findArtistByUserKeyEmail(string: String) : Artist
}