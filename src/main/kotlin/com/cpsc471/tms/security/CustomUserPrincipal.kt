package com.cpsc471.tms.security

import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.data.repository.users.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserPrincipal(private val user: User) : UserDetails {
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
        return user.userKey.email?: ""
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password?: ""
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}