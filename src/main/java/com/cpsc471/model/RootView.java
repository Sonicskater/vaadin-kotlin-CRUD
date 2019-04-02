package com.cpsc471.model;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route()
public class RootView extends Div {
    public RootView(){
        setText("Hello world!");
    }
}