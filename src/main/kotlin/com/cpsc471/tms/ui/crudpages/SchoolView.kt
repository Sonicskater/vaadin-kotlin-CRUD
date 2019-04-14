package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.institute.School
import com.cpsc471.tms.data.repository.institute.SchoolRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired


@Route("schools", layout = BaseAppLayout::class)
class SchoolView(@Autowired repo: SchoolRepository) : VerticalLayout(){



    init {
        add(CrudPage(School::class.java,repo))

    }

}
