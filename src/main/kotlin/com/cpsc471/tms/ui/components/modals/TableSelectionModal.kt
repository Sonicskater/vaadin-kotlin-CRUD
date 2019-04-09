package com.cpsc471.tms.ui.components.modals

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TableSelectionModal<T : DBAbstract>(
        classT:  Class<T>,
        dbList: MutableList<T>
) : Dialog() {


    val dbObjectList = DBObjectList(classT)
    val verticalLayout = VerticalLayout()

    init {
        add(verticalLayout)
        verticalLayout.add(dbObjectList)
        verticalLayout.add(Button("Confirm",VaadinIcon.CHECK_CIRCLE.create()){
            dbObjectList.selected()?.let { dbList.add(it) }
            close()
        })
        val repository = classT.newInstance().getRepo(classT,classT.newInstance().getKeyType())
        val displayData = (repository.findAll() as MutableList<T>)
        displayData.removeAll(dbList)
        dbObjectList.setItems(displayData)
    }
}