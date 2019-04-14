package com.cpsc471.tms.ui.components.modals

import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectList
import com.cpsc471.tms.ui.components.TableField
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TableSelectionModal<T : DBAbstract>(
        classT:  Class<T>,
        tableField: TableField<T>
) : Dialog() {


    val dbObjectList = DBObjectList(classT)
    val verticalLayout = VerticalLayout()

    init {
        add(verticalLayout)
        verticalLayout.add(dbObjectList)
        verticalLayout.add(Button("Confirm",VaadinIcon.CHECK_CIRCLE.create()){
            dbObjectList.selected()?.let { tableField.dbList.add(it) }
            tableField.update()
            close()
        })
        val repository = classT.newInstance().repo(classT,classT.newInstance().keyType())
        val displayData = (repository.findAll() as MutableList<T>)
        displayData.removeAll(tableField.dbList)
        dbObjectList.setItems(displayData)
    }
}