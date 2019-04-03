package com.cpsc471.tms

import com.cpsc471.tms.data.repos.UserRepository
import com.cpsc471.tms.data.types.Artist
import com.cpsc471.tms.data.types.Manager
import com.cpsc471.tms.data.types.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
)
    : UserDetailsService{
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(p0: String?): UserDetails {
        if(p0.isNullOrBlank()){
            throw UsernameNotFoundException(p0)
        }
        val user = userRepository.findByEmail(p0)

        return CustomUserPrincipal(user)
    }


}

class CustomUserPrincipal(private val user: User) : UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return when(user){
            is Artist -> mutableListOf(GrantedAuthority { "ROLE_USER"}, GrantedAuthority {"ROLE_ARTIST"})
            is Manager -> mutableListOf(GrantedAuthority { "ROLE_USER"}, GrantedAuthority {"ROLE_MANAGER"})
            else -> mutableListOf(GrantedAuthority { "ROLE_USER" })
        }
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}