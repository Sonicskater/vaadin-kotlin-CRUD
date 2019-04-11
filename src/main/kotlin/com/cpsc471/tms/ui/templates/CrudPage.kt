package com.cpsc471.tms.ui.templates

import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.ui.components.DBObjectForm
import com.cpsc471.tms.ui.components.DBObjectList
import com.cpsc471.tms.ui.components.modals.EditingModal
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.server.ErrorHandler
import com.vaadin.flow.server.VaadinSession
import org.springframework.data.repository.CrudRepository

class CrudPage<T : DBAbstract, V>(
        private val classT : Class<T>,
        private val repository: CrudRepository<T,V>
) : VerticalLayout() {


    private val dbObjectList = DBObjectList(classT)

    private val dbObjectForm = DBObjectForm(classT)

    private var selectedItem: T = classT.newInstance()

    private val create = Button("Create", VaadinIcon.PLUS_CIRCLE.create()){
        selectedItem = classT.newInstance()
        val modal = EditingModal(classT,selectedItem, true){
            repository.save(selectedItem)
            dbObjectList.setItems(getData())
        }
        modal.open()
    }

    private val edit = Button("Edit", VaadinIcon.PENCIL.create()){
        selectedItem = dbObjectList.selected() ?: return@Button
        val modal = EditingModal(classT,selectedItem){
            repository.save(selectedItem)
            dbObjectList.setItems(getData())
        }
        modal.open()
    }

    private val delete = Button("Delete", VaadinIcon.TRASH.create()){
        repository.delete(dbObjectList.selected() ?: return@Button )
        dbObjectList.setItems(getData())
        dbObjectForm.clear()
    }

    init {
        VaadinSession.getCurrent().errorHandler = ErrorHandler {

        }
        add(dbObjectList)
        dbObjectList.addSelectionListener {

            val firstSelectedItem = it.firstSelectedItem

            if (firstSelectedItem.isPresent){

                selectedItem = firstSelectedItem.get()
                dbObjectForm.setObject(selectedItem).render()

            }
        }
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        add(HorizontalLayout(create,edit,delete))

        add(dbObjectForm)

        dbObjectList.setItems(getData())
    }


    fun getData() : List<T>{
        return repository.findAll() as List<T>
    }
}