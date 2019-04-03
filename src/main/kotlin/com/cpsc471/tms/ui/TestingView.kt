package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.types.User
import com.cpsc471.tms.ui.components.ArtistListViewer
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.Grid
import org.springframework.beans.factory.annotation.Autowired

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

@Route("")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
class TestingView(@Autowired repo: ArtistRepository) : VerticalLayout() {


    init {
        val n = ArtistListViewer(repo, Grid.SelectionMode.SINGLE)
        n.listArtists(repo.findArtistsByFirstNameContainsOrLastNameContains("D", "H"))
        add(n)

        val button = Button("Click me")
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        add(button)

        val principal = SecurityContextHolder.getContext().authentication.principal

        val username = when (principal) {
            is UserDetails -> principal.username
            else -> principal.toString()
        }
        val usertype = when (principal) {
            is UserDetails -> principal.authorities
            else -> mutableSetOf()
        }


        add(Text(username))

        add(Text(usertype.toString()))


    }

}
