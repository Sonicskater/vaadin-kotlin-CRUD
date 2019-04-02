package com.cpsc471.view;

import com.cpsc471.model.types.School;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);
        Grid<School> grid = new Grid(School.class);
        add(grid);
        grid.removeColumnByKey("applicationSchools");
        grid.removeColumnByKey("ID");
    }

}
