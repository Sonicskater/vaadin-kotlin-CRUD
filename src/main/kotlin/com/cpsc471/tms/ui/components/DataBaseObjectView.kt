package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.converter.StringToIntegerConverter
import com.vaadin.flow.data.value.ValueChangeMode

import org.springframework.data.repository.CrudRepository
import java.lang.reflect.Field

class DataBaseObjectView<T : Any, V>(
        private val classT: Class<T>,
        private val mode: ObjectViewModes,
        private val repo: CrudRepository<T, V>,
        var selected: T = classT.newInstance(),
        var callback : () -> Unit = {}
) : VerticalLayout() {
    private val formLayout : FormLayout = FormLayout()
    private var binder : Binder<T> = Binder(classT)

    var item: T
    init {

        add(formLayout)
        item  = classT.newInstance()
        when(mode){
            ObjectViewModes.CREATE -> {
                display()
                addCreateBar()
            }

            ObjectViewModes.READ -> {
                addReadBar()
            }
            ObjectViewModes.UPDATE -> {
                item = selected
                addUpdateBar()
                display()
            }
        }



    }

    private fun addCreateBar() {
        add(Button("Save", VaadinIcon.PLUS.create()){
            binder.validate()
            binder.writeBean(item)
            repo.save(item)

            add(Notification("Created Object"))

            callback()

        })
    }


    private fun addReadBar() {
        add(Button("Create", VaadinIcon.PLUS.create()){
            val modal = DataBaseListAdditionModal(classT, {
                callback()
                display()}
                    ,repo)
            modal.open()
        })
        add(Button("Edit", VaadinIcon.PENCIL.create()){
            val modal = DataBaseListEditModal(classT, {
                callback()
                display()}
                    ,repo,item)
            modal.open()
        })
    }

    private fun addUpdateBar() {
        binder.readBean(item)
        add(Button("Save", VaadinIcon.CHECK_CIRCLE.create()){

            binder.validate()
            binder.writeBean(item)
            repo.save(item)
            callback()
        })
    }



    fun display(selectedSingle: T = item ) {
        item = selectedSingle
        formLayout.removeAll()
        binder = Binder(classT)
        if (item != null) {
            addReflectedFields(classT.superclass.declaredFields)
            addReflectedFields(classT.declaredFields)
        }
        binder.readBean(item)
    }

    private fun addReflectedFields(declaredFields: Array<Field>, prefix : String = "") {
        for (m in declaredFields) {
            when (mode) {
                ObjectViewModes.CREATE -> addReflectedChangable(m, prefix)
                ObjectViewModes.READ -> addReflectedReadOnly(m, prefix)
                ObjectViewModes.UPDATE -> addReflectedChangable(m, prefix)
                }
            }

    }

    private fun addReflectedReadOnly(m: Field, prefix: String){
        when {
            m.getAnnotation(Display::class.java) != null || m.getAnnotation(DisplayDetail::class.java) != null -> {
                val field = TextField()
                field.isReadOnly = true
                field.valueChangeMode = ValueChangeMode.EAGER
                if (m.type == Int::class.java){
                    binder.forField(field).withConverter(StringToIntegerConverter("Invalid Integer")).bind(prefix + m.name)
                }else {
                    binder.forField(field).bind(prefix + m.name)
                }
                formLayout.addFormItem(field,m.name)
            }
            m.getAnnotation(DisplayComposite::class.java) != null -> {

                addReflectedFields(m.type.declaredFields, m.name + ".")

            }
            m.getAnnotation(DisplayList::class.java) != null -> {
                val type = m.getAnnotation(DisplayList::class.java).type
                formLayout.add(SubDataListView(Grid.SelectionMode.SINGLE, type.java))
            }
        }
    }

    private fun addReflectedChangable(m: Field, prefix: String){
        when {
            m.getAnnotation(Editable::class.java) != null -> {
                val field = TextField()
                field.isReadOnly = false
                field.valueChangeMode = ValueChangeMode.EAGER
                if (m.type == Int::class.java){
                    binder.forField(field).withConverter(StringToIntegerConverter("Invalid Integer")).bind(prefix + m.name)
                }else {
                    binder.forField(field).bind(prefix + m.name)
                }
                formLayout.addFormItem(field,m.name)
            }
            m.getAnnotation(EditableComposite::class.java) != null -> {

                addReflectedFields(m.type.declaredFields, m.name + ".")

            }
            m.getAnnotation(EditableList::class.java) != null -> {
                val type = m.getAnnotation(EditableList::class.java).type
                add(SubDataListView(Grid.SelectionMode.SINGLE, type.java))
            }
        }
    }

}
