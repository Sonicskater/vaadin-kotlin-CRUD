package com.cpsc471.tms.ui

import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.data.repos.SchoolRepository
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.components.DataBaseListView
import com.cpsc471.tms.ui.components.DataBaseObjectView
import com.cpsc471.tms.ui.components.ObjectViewModes
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.*
import com.vaadin.flow.router.Route
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo
import com.vaadin.server.Page
import org.springframework.beans.factory.annotation.Autowired

@Route("schools",layout = BaseAppLayout::class)
class SchoolList(@Autowired var repo : SchoolRepository) : VerticalLayout() {

    val dataBaseObjectView : DataBaseObjectView<School,InstituteKey>
    val dataBaseListView : DataBaseListView<School,InstituteKey>
    init {
        dataBaseObjectView = DataBaseObjectView(School::class.java,ObjectViewModes.READ,repo)
        dataBaseListView = DataBaseListView(repo, Grid.SelectionMode.SINGLE,School::class.java){
            dataBaseObjectView.display(it)
        }
        dataBaseObjectView.callback = {
            println("refreshing list")
            dataBaseListView.list(repo.findAll() as List<School>)
        }
        add(dataBaseListView)
        add(dataBaseObjectView)

    }
}
