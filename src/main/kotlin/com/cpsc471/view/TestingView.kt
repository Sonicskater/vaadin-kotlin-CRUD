package com.cpsc471.view

import com.cpsc471.model.repos.ArtistRepository
import com.cpsc471.model.types.Artist
import com.cpsc471.model.types.School
import com.cpsc471.view.components.ArtistListViewer
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import org.springframework.beans.factory.annotation.Autowired

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import org.springframework.transaction.annotation.Transactional

@Route("test")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
class TestingView(@Autowired repo: ArtistRepository) : VerticalLayout() {


    init {
        val n = ArtistListViewer(repo, Grid.SelectionMode.SINGLE)
        n.listArtists(repo.findArtistsByFirstNameContainsOrLastNameContains("D", "H"))
        add(n)

        val button = Button("Click me")
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        add(button)
    }

}
