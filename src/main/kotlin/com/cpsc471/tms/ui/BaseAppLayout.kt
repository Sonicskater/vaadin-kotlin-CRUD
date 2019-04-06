package com.cpsc471.tms.ui

import com.github.appreciated.app.layout.behaviour.Behaviour
import com.github.appreciated.app.layout.builder.AppLayoutBuilder
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder
import com.github.appreciated.app.layout.entity.Section
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout
import com.vaadin.annotations.Viewport
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.AppLayoutMenu
import com.vaadin.flow.component.applayout.AppLayoutMenuItem
import com.vaadin.flow.component.html.H3
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.router.Route
import com.vaadin.ui.Component
import com.vaadin.ui.Notification
import com.vaadin.ui.VerticalLayout
@Route("")
class BaseAppLayout : AppLayoutRouterLayout() {

    private var notifications : DefaultNotificationHolder
    private var badge : DefaultBadgeHolder

    init{
        notifications =  DefaultNotificationHolder{}
        badge =  DefaultBadgeHolder(5)
        for(i in 1..5){
            notifications.addNotification(DefaultNotification("TEST NOTIFICATION $i", "asdfgasdfasdfasdfasdgaeibaivbluivufuahdu"))
        }

        var menuElement = LeftNavigationItem("",VaadinIcon.MENU.create(), NewView::class.java )
        badge.bind(menuElement.badge)
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("Test Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(AppBarNotificationButton(VaadinIcon.BELL,notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(LeftHeaderItem("HEADER 1",
                                "Version ?",
                                ""
                        ), Section.HEADER)
                        .addToSection(LeftClickableItem("CLICKABLE 1",
                                VaadinIcon.CALC.create(),
                                {}
                        ),Section.HEADER)
                        .add(LeftNavigationItem("Home", VaadinIcon.HOME.create(), NewView::class.java))
                        .add(LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), NewView2::class.java))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftNavigationItem("Charts",
                                                VaadinIcon.SPLINE_CHART.create(),
                                                NewView2::class.java
                                                ))
                                        .add(LeftNavigationItem("Contact",
                                                VaadinIcon.CONNECT.create(),
                                                NewView::class.java
                                                ))
                                        .add(LeftNavigationItem("More",
                                                VaadinIcon.COG.create(),
                                                NewView2::class.java
                                                ))
                                        .build())
                                .add(LeftNavigationItem("Contact1",
                                        VaadinIcon.CONNECT.create(),
                                        NewView::class.java
                                        ))
                                .add(LeftNavigationItem("More1", VaadinIcon.COG.create(), NewView2::class.java))
                                .build())
                        .add(menuElement)
                        .addToSection(LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create()
                        ) {Notification.show("onClick ...")}, Section.FOOTER)
                .build()
                ).build())

    }

}