package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.users.ArtistRepository
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("self", layout = BaseAppLayout::class)
class SelfView(@Autowired repo: ArtistRepository) : VerticalLayout() {


    init {

    }

}
