package com.cpsc471.tms.ui.components


import com.vaadin.flow.component.customfield.CustomField
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class StringField(root : VerticalLayout, editable: Boolean) : CustomField<MutableList<String>>() {

    var backingField : MutableList<String> = mutableListOf()

    override fun setPresentationValue(p0: MutableList<String>?) {
        backingField = p0?: mutableListOf()
        dateFieldUI.update()
    }

    override fun generateModelValue(): MutableList<String> {
        return backingField
    }


    override fun getValue(): MutableList<String> {
        return backingField
    }

    val dateFieldUI = StringFieldUI(this, editable)
    init{
        root.add(dateFieldUI)
    }
}