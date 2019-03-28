package com.cpsc471.model;

import com.cpsc471.model.types.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "test")
public class MainView extends VerticalLayout
{

    private EmployeeEditor editor;
    //Grid<User> grid;
    TextField filter;
    private Button addNewBtn;

    public MainView (EmployeeEditor editor) {
        setSizeFull();
        this.editor = editor;
        this.filter = new TextField();
        //this.grid = new Grid<>(User.class);
        this.addNewBtn = new Button("New employee", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, editor);

        //grid.setHeight("200px");
        //grid.setColumns("id", "firstName", "lastName");
        //grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);




    }
}
