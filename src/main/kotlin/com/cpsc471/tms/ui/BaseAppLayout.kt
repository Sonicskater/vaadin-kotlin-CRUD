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
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.router.Route
import com.vaadin.ui.Notification

@Route("")
class BaseAppLayout : AppLayoutRouterLayout() {

    private var notifications : DefaultNotificationHolder = DefaultNotificationHolder{}
    private var badge : DefaultBadgeHolder

    init{
        badge =  DefaultBadgeHolder(5)
        for(i in 1..5){
            notifications.addNotification(DefaultNotification("TEST NOTIFICATION $i", "asdfgasdfasdfasdfasdgaeibaivbluivufuahdu"))
        }

        val menuElement = LeftNavigationItem("",VaadinIcon.MENU.create(), SchoolView::class.java )
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
                                VaadinIcon.CALC.create()
                        ) {},Section.HEADER)
                        .add(LeftNavigationItem("Schools", VaadinIcon.HOME.create(), SchoolView::class.java))
                        .add(LeftNavigationItem("Projects", VaadinIcon.GROUP.create(), ProjectView::class.java))
                        .add(LeftNavigationItem("Vehicles", VaadinIcon.CAR
                                .create(), VehicleView::class.java))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftNavigationItem("Charts",
                                                VaadinIcon.SPLINE_CHART.create(),
                                                SchoolView::class.java
                                                ))
                                        .add(LeftNavigationItem("Contact",
                                                VaadinIcon.CONNECT.create(),
                                                SchoolView::class.java
                                                ))
                                        .add(LeftNavigationItem("More",
                                                VaadinIcon.COG.create(),
                                                SchoolView::class.java
                                                ))
                                        .build())
                                .add(LeftNavigationItem("Contact1",
                                        VaadinIcon.CONNECT.create(),
                                        SchoolView::class.java
                                        ))
                                .add(LeftNavigationItem("More1", VaadinIcon.COG.create(), SchoolView::class.java))
                                .build())
                        .add(menuElement)
                        .addToSection(LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create()
                        ) {Notification.show("onClick ...")}, Section.FOOTER)
                .build()
                ).build())

        element.setAttribute("theme", "dark")

    }


    override fun onAttach(attachEvent: AttachEvent) {
        super.onAttach(attachEvent)
        /**
         * Using the @Theme Annotation to set the Dark Theme causes issues with shadows which will appear in
         * the wrong color making them seemingly invisible. Instead do it the following way as long as the issue is not
         * solved (https://github.com/vaadin/flow/issues/4765)
         */
        ui.get().page.executeJavaScript("document.documentElement.setAttribute(\"theme\",\"dark\")")
    }

}