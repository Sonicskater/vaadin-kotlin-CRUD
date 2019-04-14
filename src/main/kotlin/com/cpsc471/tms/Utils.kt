package com.cpsc471.tms

import com.cpsc471.tms.data.repository.users.User
import org.springframework.security.core.GrantedAuthority

fun hasRole(role: String): Boolean {
    val authorities = SecurityHelper.principal.authorities as Collection<GrantedAuthority>
    var hasRole = false
    for (authority in authorities) {
        hasRole = authority.authority == role
        if (hasRole) {
            break
        }
    }
    return hasRole
}

fun getUser() : User {
    return RepoHelper.userRepository.findByUserKeyEmail(SecurityHelper.principal.username)
}