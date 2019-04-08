package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon
import org.springframework.data.repository.CrudRepository

class DataBaseListAdditionModal<T : Any, V>(

        classT: Class<T>,
        callback: () -> Unit,
        repo: CrudRepository<T, V>
) : Dialog() {

    val objectView: DataBaseObjectView<T, V>

    lateinit var selected : T
    init {
        objectView = DataBaseObjectView(classT,ObjectViewModes.CREATE, repo){callback()}
        add(Text("Creating new " +classT.simpleName ))
        add(objectView)

        callback()

        close()

    }

}