package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.repository.DBAbstract
import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.orderedlayout.VerticalLayout


class ObjectField<T : DBAbstract>(val classT: Class<T>, initial: T?, root : VerticalLayout, readOnly : Boolean = true) : CustomField<T>(){

    private var backingField : T = initial?:classT.newInstance()

    private var dbObjectForm : DBObjectForm<T> = DBObjectForm(classT, editable = false, creatable = false, verbose = false)

    init {
        val objectFieldUI = ObjectFieldUI(classT,backingField,readOnly){
            backingField = it
            update()
        }
        root.add(objectFieldUI)
        dbObjectForm = objectFieldUI.dbObjectForm

        update()
    }

    override fun generateModelValue(): T {
        return backingField
    }

    override fun getValue(): T {
        return backingField
    }


    override fun setPresentationValue(p0: T) {
        backingField = p0
        update()
    }

    private fun update() {
        dbObjectForm.setObject(backingField)
        dbObjectForm.render()
    }

}