package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.icon.VaadinIcon
import org.springframework.data.repository.CrudRepository
@Deprecated("")
class DataBaseListEditModal<T : Any, V>(

        classT: Class<T>,
        callback: () -> Unit,
        repo: CrudRepository<T, V>,
        item: T = classT.newInstance()
) : Dialog() {

    val objectView: DataBaseObjectView<T, V>

    lateinit var selected : T
    init {
        objectView = DataBaseObjectView(classT,ObjectViewModes.UPDATE, repo,item){callback()}
        add(Text("Creating new " +classT.simpleName ))
        add(objectView)

        callback()

        close()

    }

}