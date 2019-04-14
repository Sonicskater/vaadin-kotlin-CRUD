package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplication
import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplicationsRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("school-grants", layout = BaseAppLayout::class)
class SchoolGrantsView(@Autowired repo: SchoolGrantApplicationsRepository) : VerticalLayout(){

    init {
        add(CrudPage(SchoolGrantApplication::class.java,repo))

    }

}
