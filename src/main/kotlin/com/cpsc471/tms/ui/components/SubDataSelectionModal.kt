package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.VaadinIcon
import org.springframework.data.repository.CrudRepository
@Deprecated("")
class SubDataSelectionModal<T : Any, V>(

        classT : Class<T>,
        repository: CrudRepository<T,V>,
        originData: Set<T>,
        callback: (it : ClickEvent<Button>) -> Unit
) : Dialog() {
    
    val subData : SubDataListView<T>
    
    val select : Button


    lateinit var selected : T
    init {
        subData = SubDataListView(Grid.SelectionMode.SINGLE,classT){
            selected = it
        }
        subData.list(repository.findAll() as List<T>, originData)
        select = Button("Select",VaadinIcon.PLUS.create()){

        }


    }

}