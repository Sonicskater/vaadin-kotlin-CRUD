package com.cpsc471.tms.ui

import com.cpsc471.tms.hasRole
import com.cpsc471.tms.ui.crudpages.*
import com.github.appreciated.app.layout.behaviour.Behaviour
import com.github.appreciated.app.layout.builder.AppLayoutBuilder
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem
import com.github.appreciated.app.layout.entity.Section
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.VaadinService
import com.vaadin.server.Page
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler


@Route("home")
class BaseAppLayout : AppLayoutRouterLayout() {

    init{

        val appBar = AppLayoutBuilder.get(Behaviour.LEFT_RESPONSIVE_HYBRID).withTitle("Theatre Management System").withAppBar(AppBarBuilder.get().build())

        init(appBar.withAppMenu(LeftAppMenuBuilder
                        .get()
                .addToSection(LeftClickableItem("Log Out",VaadinIcon.EXIT.create()){
                    VaadinService.getCurrentRequest().wrappedSession.invalidate()
                    SecurityContextLogoutHandler().logout((VaadinService.getCurrentRequest() as com.vaadin.flow.server.VaadinServletRequest).httpServletRequest, null, null)
                    Page.getCurrent().setLocation("/")
                }, Section.FOOTER)
                .buildMenus()
                .build()
                ).build())

        element.setAttribute("theme", "dark")

    }
    private fun LeftAppMenuBuilder.buildMenus(): LeftAppMenuBuilder {
        return when {
            hasRole("ROLE_ARTIST") -> this.userFunctions()
            hasRole("ROLE_MANAGER") -> this.userFunctions().adminFunctions()
            else -> this
        }
    }

    private fun LeftAppMenuBuilder.adminFunctions(): LeftAppMenuBuilder {

        return this
                .add(LeftNavigationItem("Projects", VaadinIcon.GROUP.create(), ProjectView::class.java))
                .add(LeftNavigationItem("Project Matcher", VaadinIcon.SEARCH.create(), VehicleView::class.java))
                .add(LeftNavigationItem("Vehicles", VaadinIcon.CAR.create(), VehicleView::class.java))
                .add(LeftSubMenuBuilder.get("Users", VaadinIcon.USERS.create())
                        .add(LeftNavigationItem("All Users", VaadinIcon.USER.create(), UsersView::class.java))
                        .add(LeftNavigationItem("Artists", VaadinIcon.PAINTBRUSH.create(), ArtistView::class.java))
                        .add(LeftNavigationItem("Managers", VaadinIcon.CLIPBOARD_USER.create(), ManagerView::class.java))
                        .add(LeftNavigationItem("Unassigned", VaadinIcon.QUESTION_CIRCLE.create(), UnassignedView::class.java))
                        .build())
                .add(LeftSubMenuBuilder.get("Institutions", VaadinIcon.WORKPLACE.create())
                        .add(LeftNavigationItem("All Institutions", VaadinIcon.OFFICE.create(), InstituteView::class.java))
                        .add(LeftNavigationItem("Schools", VaadinIcon.HOME.create(), SchoolView::class.java))
                        .add(LeftNavigationItem("Funding Sources", VaadinIcon.DIPLOMA.create(), FundingSourceView::class.java))
                        .build())
                .add(LeftSubMenuBuilder.get("Grants", VaadinIcon.MONEY.create())
                        .add(LeftNavigationItem("School Grant", VaadinIcon.FORM.create(), SchoolGrantsView::class.java))
                        .add(LeftNavigationItem("Grants", VaadinIcon.MONEY_DEPOSIT.create(), SelfGrantsView::class.java))
                        .build())
                .add(LeftSubMenuBuilder.get("Contact Managment", VaadinIcon.LIST.create())
                        .add(LeftNavigationItem("Contacts", VaadinIcon.USER_CARD.create(), ContactsView::class.java))
                        .add(LeftNavigationItem("Contact Info", VaadinIcon.PLUS_CIRCLE.create(), ContactDetailsView::class.java))
                        .build())
                .add(LeftNavigationItem("Billing", VaadinIcon.MONEY_EXCHANGE.create(), BillingView::class.java))

    }

    private fun LeftAppMenuBuilder.userFunctions(): LeftAppMenuBuilder{
        return this
                .add(LeftNavigationItem("Me", VaadinIcon.USER_CARD.create(), MeView::class.java))
                .add(LeftNavigationItem("My Projects", VaadinIcon.MODAL_LIST.create(), MyProjectsView::class.java))
                .add(LeftNavigationItem("Add to Vehicle Logs", VaadinIcon.LINES_LIST.create(), VehicleLogView::class.java))

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