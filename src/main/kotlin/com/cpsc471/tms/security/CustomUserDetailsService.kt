package com.cpsc471.tms.security

import com.cpsc471.tms.SecurityHelper
import com.cpsc471.tms.data.repository.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService
    : UserDetailsService{
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(p0: String?): UserDetails {
        if(p0.isNullOrBlank()){
            throw UsernameNotFoundException(p0)
        }
        val user = userRepository.findByUserKeyEmail(p0)
        SecurityHelper.principal = CustomUserPrincipal(user)
        return CustomUserPrincipal(user)
    }


}

