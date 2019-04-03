package com.cpsc471.view;

import com.cpsc471.model.repos.ArtistRepository;
import com.cpsc471.model.types.Artist;
import com.cpsc471.model.types.School;
import com.cpsc471.view.components.ArtistListViewer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Route("")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {


    public MainView(ArtistRepository repo) {
        ArtistListViewer n = new ArtistListViewer(repo);
        n.listArtists(repo.findArtistsByFirstNameOrLastNameContains("D","H"));
        add(n);

        Button button = new Button("Click me");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);
    }

}
