package com.cpsc471.tms

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

fun hasRole(role: String): Boolean {
    val authorities = SecurityContextHolder.getContext().authentication.authorities as Collection<GrantedAuthority>
    var hasRole = false
    for (authority in authorities) {
        hasRole = authority.authority == role
        if (hasRole) {
            break
        }
    }
    return hasRole
}