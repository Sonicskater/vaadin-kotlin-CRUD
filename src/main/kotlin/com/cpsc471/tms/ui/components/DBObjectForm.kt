package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.helger.commons.exception.InitializationException
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.HtmlComponent
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.converter.Converter
import com.vaadin.flow.data.converter.StringToIntegerConverter
import com.vaadin.flow.data.value.ValueChangeMode

import org.springframework.data.repository.CrudRepository
import java.lang.reflect.Field
import javax.print.attribute.standard.DateTimeAtCreation

class DBObjectForm<T : DBAbstract>(
        private val classT: Class<T>,
        val editable: Boolean = false,
        private val creatable: Boolean = false,
        var verbose: Boolean = true
) : VerticalLayout() {
    private val formLayout : FormLayout = FormLayout()
    private var binder : Binder<T> = Binder(classT)

    private lateinit var item: T

    private val verticalLayout = VerticalLayout()

    init {
        add(formLayout)
        add(verticalLayout)
    }


    fun setObject(item : T) : DBObjectForm<T> {
        this.item = item
        return this
    }
    fun save(){
        binder.writeBean(item)
    }

    fun getObject() : T{
        return item
    }

    fun clear(){
        formLayout.removeAll()
    }


    fun render() {
        if(::item.isInitialized){
            formLayout.removeAll()
            verticalLayout.removeAll()
            generateReflectedFields(classT.superclass.declaredFields)
            generateReflectedFields(classT.declaredFields)
            binder.readBean(item)
        }else{
            throw InitializationException("No item to render")
        }
    }

    private fun generateReflectedFields(fields: Array<Field>, prefix: String = ""){
        for ( field: Field in fields){
            val annotation = field.getAnnotation(Display::class.java)
            if (annotation != null) {
                if (annotation.category != DisplayCategory.VERBOSE || verbose) {
                    when (annotation.clasif) {
                        DisplayTypeClasif.PRIMITIVE -> {
                            val textField = TextField()
                            textField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)
                            textField.valueChangeMode = ValueChangeMode.EAGER
                            if (field.type == Int::class.java) {
                                binder
                                        .forField(textField)
                                        .withConverter(StringToIntegerConverter("Invalid Integer"))
                                        .bind(prefix + field.name)
                            } else {
                                binder
                                        .forField(textField)
                                        .bind(prefix + field.name)
                            }
                            formLayout.addFormItem(textField, field.name.capitalize())
                        }
                        DisplayTypeClasif.OBJECT -> {
                            TODO()
                        }

                        DisplayTypeClasif.COMPOSITE -> {
                            generateReflectedFields(field.type.declaredFields, field.name + ".")
                        }
                        DisplayTypeClasif.LIST -> {
                            val tableField = TableField(annotation.type.java, editable,field.name.capitalize(),verticalLayout)


                            binder.forField(tableField).bind(prefix + field.name)
                            formLayout.addFormItem(tableField,"")

                        }
                    }

                }
            }
        }
    }

}
