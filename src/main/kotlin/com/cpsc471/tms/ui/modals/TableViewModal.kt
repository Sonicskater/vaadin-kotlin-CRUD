package com.cpsc471.tms.ui.modals

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TableViewModal<T : DBAbstract>(
        classT : Class<T>,
        dbList : MutableList<T>,
        readOnly: Boolean = true) : VerticalLayout() {

    val dbObjectList : DBObjectList<T> = DBObjectList(classT)
    val verticalLayout = VerticalLayout()
    init {
        add(verticalLayout)
        verticalLayout.add(dbObjectList)

        if(!readOnly){
            verticalLayout.add(Button("Add", VaadinIcon.PLUS_CIRCLE.create()){

            })
            verticalLayout.add(Button("Remove", VaadinIcon.TRASH.create()){

            })
        }
    }
}