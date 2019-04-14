package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.institute.Institute
import com.cpsc471.tms.data.repository.institute.InstituteRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("institutes", layout = BaseAppLayout::class)
class InstituteView(@Autowired repo : InstituteRepository) : VerticalLayout() {

    init {
        add(CrudPage(Institute::class.java,repo ))
    }
}
