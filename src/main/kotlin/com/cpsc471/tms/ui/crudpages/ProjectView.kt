package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.data.repository.projects.ProjectRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("projects", layout = BaseAppLayout::class)
class ProjectView(@Autowired repo: ProjectRepository) : VerticalLayout()  {

    init {
        add(CrudPage(Project::class.java,repo))

    }

}
