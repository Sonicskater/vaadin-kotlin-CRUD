package com.cpsc471.tms.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.RequestMatcher

/**
 * Configures spring security, doing the following:
 *  * Bypass security checks for static resources,
 *  * Restrict access to the application, allowing only logged in users,
 *  * Set up the login form
 */
@EnableWebSecurity
@Configuration
open class SecurityConfiguration(@Autowired
                                 private val userDetailsService: CustomUserDetailsService) : WebSecurityConfigurerAdapter() {

    /**
     * Require login to access internal pages and configure login form.
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // Not using Spring CSRF here to be able to use plain HTML for the login page
        http.csrf().disable()

                // Register our CustomRequestCache, that saves unauthorized access attempts, so
                // the user is redirected after login.
                .requestCache().requestCache(CustomRequestCache())

                // Restrict access to our application.
                .and().authorizeRequests()

                // Allow all flow internal requests.
                .requestMatchers(RequestMatcher { SecurityUtils.isFrameworkInternalRequest(it) }).permitAll()

                // Allow all requests by logged in users.
                .anyRequest().authenticated()

                // Configure the login page.
                .and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)

                // Configure logout
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL)
    }

    /**
     * Allows access to static resources, bypassing Spring security.
     */
    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers(
                // Vaadin Flow static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                // icons and images
                "/icons/**",
                "/images/**",

                // (development mode) static resources
                "/frontend/**",

                // (development mode) webjars
                "/webjars/**",

                // (development mode) H2 debugging console
                "/h2/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**")
    }

    companion object {
        private const val LOGIN_PROCESSING_URL = "/login"
        private const val LOGIN_FAILURE_URL = "/login"
        private const val LOGIN_URL = "/login"
        private const val LOGOUT_SUCCESS_URL = "/login"
    }

    @Bean
    open fun authenticationProvider() : DaoAuthenticationProvider{
        val authProvider = DaoAuthenticationProvider()

        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider

    }

    @Bean
    open fun encoder() : PasswordEncoder{
        return BCryptPasswordEncoder(11)
    }

}