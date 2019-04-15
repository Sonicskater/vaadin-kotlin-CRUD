package com.cpsc471.tms.ui.components


import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import java.time.LocalDate

class DateField(root : VerticalLayout, editable: Boolean) : CustomField<MutableList<LocalDate>>() {

    var backingField : MutableList<LocalDate> = mutableListOf()

    override fun setPresentationValue(p0: MutableList<LocalDate>?) {
        backingField = p0?: mutableListOf()
        dateFieldUI.update()
    }

    override fun generateModelValue(): MutableList<LocalDate> {
        return backingField
    }


    override fun getValue(): MutableList<LocalDate> {
        return backingField
    }

    val dateFieldUI = DateFieldUI(this, editable)
    init{
        root.add(dateFieldUI)
    }
}