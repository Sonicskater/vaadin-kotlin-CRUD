package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.repos.ProjectRepository
import com.cpsc471.tms.data.repos.SchoolRepository
import com.cpsc471.tms.data.types.Artist
import com.cpsc471.tms.data.types.Project
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
@Route("projects", layout = BaseAppLayout::class)
class ProjectView(@Autowired repo: ProjectRepository) : VerticalLayout() {


    init {
        add(CrudPage(Project::class.java,repo))

    }

}
