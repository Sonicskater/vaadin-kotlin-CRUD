package com.cpsc471


import org.springframework.boot.autoconfigure.SpringBootApplication

import org.springframework.context.annotation .Bean
import org.springframework.context.annotation .Configuration
import org.springframework.security.config.annotation .web.builders.HttpSecurity
import org.springframework.security.config.annotation .web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation .web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import org.springframework.boot.SpringApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer


@SpringBootApplication
class TheatreManagmentApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(TheatreManagmentApplication::class.java, *args)
}




@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    protected override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()

        http.csrf().disable()
        http.headers().frameOptions().disable()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()

        return InMemoryUserDetailsManager(user)
    }
}

@Configuration
class MvcConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("home")
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/hello").setViewName("hello")
        registry.addViewController("/login").setViewName("login")
    }

}