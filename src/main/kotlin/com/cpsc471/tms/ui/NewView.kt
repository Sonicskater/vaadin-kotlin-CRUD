package com.cpsc471.tms.ui

import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.data.repos.SchoolRepository
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.components.DataBaseListView
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.*
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired
@Deprecated("testing class")
@Route("new",layout = BaseAppLayout::class)
class NewView(@Autowired var repo : SchoolRepository) : VerticalLayout() {

    init {
        add(DataBaseListView(repo, Grid.SelectionMode.SINGLE,  School::class.java))

    }
}
