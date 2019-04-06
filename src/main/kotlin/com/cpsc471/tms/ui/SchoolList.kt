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

@Route("schools",layout = BaseAppLayout::class)
class SchoolList(@Autowired var repo : SchoolRepository) : VerticalLayout() {

    init {
        add(DataBaseListView(repo, Grid.SelectionMode.SINGLE, null, School::class.java))

    }
}
