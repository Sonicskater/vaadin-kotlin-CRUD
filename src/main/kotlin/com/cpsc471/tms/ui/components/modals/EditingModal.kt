package com.cpsc471.tms.ui.components.modals

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectForm
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon

class EditingModal<T : DBAbstract>(
        val classT : Class<T>,
        private val item : T,
        private val create : Boolean = false,
        val confirm : () -> Unit
) : Dialog() {


    private val dbObjectForm = DBObjectForm(classT, true,create)

    private val confirmButton = Button("Confirm", VaadinIcon.CHECK_CIRCLE.create()) {
        dbObjectForm.save()
        confirm()
    }

    init {
        add(dbObjectForm)
        dbObjectForm.setObject(item).render()
        add(confirmButton)
    }
}