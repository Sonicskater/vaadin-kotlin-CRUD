package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repos.SchoolRepository
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired


@Route("schools", layout = BaseAppLayout::class)
class SchoolView(@Autowired repo: SchoolRepository) : VerticalLayout() {


    init {
        add(CrudPage(School::class.java,repo))

    }

}
