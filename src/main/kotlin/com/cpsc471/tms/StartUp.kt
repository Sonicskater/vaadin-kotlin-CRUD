package com.cpsc471.tms

import com.cpsc471.tms.data.repos.ManagerRepository
import com.cpsc471.tms.data.types.Manager
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class StartUp @Autowired
constructor(private val repo: ManagerRepository) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {

        val encoder = BCryptPasswordEncoder(11)
        val manager = Manager()
        manager.userKey.email = "admin"
        manager.password = encoder.encode("password")
        manager.firstName = "ADMIN"
        manager.lastName = "ACCOUNT"
        repo.save(manager)

    }

    companion object {

        var counter: Int = 0
    }
}