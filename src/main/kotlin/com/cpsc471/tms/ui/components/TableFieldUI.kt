package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.ui.components.modals.TableSelectionModal
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TableFieldUI<T : DBAbstract>(
        classT : Class<T>,
        tableField: TableField<T>,
        readOnly: Boolean = true) : VerticalLayout() {

    val dbObjectList : DBObjectList<T> = DBObjectList(classT)
    val verticalLayout = VerticalLayout()
    val horizontalLayout = HorizontalLayout()
    init {
        add(verticalLayout)
        verticalLayout.add(dbObjectList)
        verticalLayout.add(horizontalLayout)
/*
        horizontalLayout.add(Button("View", VaadinIcon.EYE.create()){

            dbObjectList.selected()?.view(UI.getCurrent())
        })
*/
        if(!readOnly){
            horizontalLayout.add(Button("Add", VaadinIcon.PLUS_CIRCLE.create()){
                val modal = TableSelectionModal(classT, tableField)
                modal.open()
            })
            horizontalLayout.add(Button("Remove", VaadinIcon.TRASH.create()){
                tableField.dbList.remove(dbObjectList.selected())
                tableField.update()
            })
        }
    }
}