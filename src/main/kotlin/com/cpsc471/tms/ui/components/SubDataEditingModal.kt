package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.VaadinIcon

class SubDataEditingModal<T : Any>(
        
        classT : Class<T>,
        callback: (it : ClickEvent<Button>) -> Unit
) : Dialog() {
    
    val subData : SubDataListView<T>
    
    val add : Button
    val remove : Button

    lateinit var selected : T
    init {
        subData = SubDataListView(Grid.SelectionMode.SINGLE,classT){
            selected = it
        }
        add = Button("Add",VaadinIcon.PLUS.create()){

        }
        remove = Button("Remove",VaadinIcon.PLUS.create()){

        }

        add(subData,add,remove)



    }

}