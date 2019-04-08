package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.modals.TableViewModal
import com.vaadin.flow.component.AbstractField
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import javafx.scene.control.TableView


class TableField<T : DBAbstract>(val classT: Class<T>, readOnly : Boolean, name: String, root: VerticalLayout) : CustomField<MutableList<T>>() {

    private var dbObjectList : DBObjectList<T> = DBObjectList(classT)

    var dbList : MutableList<T> = mutableListOf()

    override fun setPresentationValue(p0: MutableList<T>?) {
        dbList = p0 ?: mutableListOf()
        update()
    }

    override fun generateModelValue(): MutableList<T> {
        return dbList
    }

    override fun getValue(): MutableList<T> {
        return dbList
    }

    private val verticalLayout = VerticalLayout()

    init{
        val tableViewModal = TableViewModal(classT,dbList,readOnly)
        dbObjectList = tableViewModal.dbObjectList
        root.add(tableViewModal)
        update()

    }
    private fun update(){
        dbObjectList.setItems(dbList)

    }

}