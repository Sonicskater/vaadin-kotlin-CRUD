package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.projects.ProjectRepository
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.ui.templates.CrudPage
import org.springframework.beans.factory.annotation.Autowired

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("projects", layout = BaseAppLayout::class)
class ProjectView(@Autowired repo: ProjectRepository) : VerticalLayout() {


    init {
        add(CrudPage(Project::class.java,repo))

    }

}
