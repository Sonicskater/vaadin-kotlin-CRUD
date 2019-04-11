package com.cpsc471.tms

import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.ArtistRepository
import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.data.repository.users.ManagerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class StartUp @Autowired
constructor(private val repo: ManagerRepository, private val repo2: ArtistRepository) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {

        val encoder = BCryptPasswordEncoder(11)
        val manager = Manager()
        manager.userKey.email = "admin"
        manager.password = encoder.encode("password")
        manager.firstName = "ADMIN"
        manager.lastName = "ACCOUNT"
        repo.save(manager)

        val artist = Artist()
        artist.userKey.email = "artist"
        artist.password = encoder.encode("password")
        artist.firstName = "ARTIST"
        artist.lastName = "ACCOUNT"
        repo2.save(artist)

    }

    companion object {

        var counter: Int = 0
    }
}