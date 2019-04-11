package com.cpsc471.tms.ui.components.modals

import com.cpsc471.tms.data.types.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class ObjectSelectionModal<T : DBAbstract>(
        classT:  Class<T>,
        dbObject: T,
        callback: (T) -> Unit
) : Dialog() {


    val dbObjectList = DBObjectList(classT)
    val verticalLayout = VerticalLayout()

    init {
        add(verticalLayout)
        verticalLayout.add(dbObjectList)
        verticalLayout.add(Button("Confirm",VaadinIcon.CHECK_CIRCLE.create()){
            callback(dbObjectList.selected()?: dbObject)
            close()
        })
        val repository = classT.newInstance().repo(classT,classT.newInstance().keyType())
        val displayData = (repository.findAll() as MutableList<T>)
        displayData.removeAll(listOf(dbObject))
        dbObjectList.setItems(displayData)
    }
}