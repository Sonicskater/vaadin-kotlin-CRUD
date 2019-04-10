package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.types.DBAbstract
import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.orderedlayout.VerticalLayout


class TableField<T : DBAbstract>(val classT: Class<T>, readOnly : Boolean, name: String, root: VerticalLayout) : CustomField<MutableList<T>>() {

    private var dbObjectList : DBObjectList<T> = DBObjectList(classT)

    private var dbList : MutableList<T> = mutableListOf()

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
        val tableViewModal = TableFieldUI(classT, dbList, readOnly)
        dbObjectList = tableViewModal.dbObjectList
        root.add(tableViewModal)
        update()

    }
    private fun update(){
        dbObjectList.setItems(dbList)

    }

}