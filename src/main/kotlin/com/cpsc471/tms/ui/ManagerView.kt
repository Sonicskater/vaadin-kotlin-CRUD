package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.data.repository.users.ManagerRepository
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("managers", layout = BaseAppLayout::class)
class ManagerView(@Autowired repo: ManagerRepository) : VerticalLayout() {


    init {
        add(CrudPage(Manager::class.java,repo))

    }

}
