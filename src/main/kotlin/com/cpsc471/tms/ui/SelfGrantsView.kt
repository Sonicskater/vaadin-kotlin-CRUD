package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.selfGrants.SelfGrant
import com.cpsc471.tms.data.repository.selfGrants.SelfGrantRepository
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("grants", layout = BaseAppLayout::class)
class SelfGrantsView(@Autowired repo: SelfGrantRepository) : VerticalLayout() {


    init {
        add(CrudPage(SelfGrant::class.java,repo))

    }

}
