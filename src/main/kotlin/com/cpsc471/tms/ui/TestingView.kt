package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.repos.SchoolRepository
import com.cpsc471.tms.data.types.Artist
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.data.types.User
import com.cpsc471.tms.ui.components.ArtistListViewer
import com.cpsc471.tms.ui.components.DataBaseListView
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.grid.Grid
import org.springframework.beans.factory.annotation.Autowired

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.ParentLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import org.jsoup.Connection
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
@Deprecated("testing class")
@Route("testing", layout = BaseAppLayout::class)
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
class TestingView(@Autowired repo: SchoolRepository) : VerticalLayout() {


    init {
        add(CrudPage(School::class.java,repo))

    }

}
