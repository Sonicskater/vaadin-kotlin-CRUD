package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.ArtistRepository
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("artists", layout = BaseAppLayout::class)
class ArtistView(@Autowired repo: ArtistRepository) : VerticalLayout() {


    init {
        add(CrudPage(Artist::class.java,repo))

    }

}
