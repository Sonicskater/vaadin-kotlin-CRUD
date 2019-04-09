package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.helger.commons.exception.InitializationException
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.converter.StringToIntegerConverter
import com.vaadin.flow.data.value.ValueChangeMode
import java.lang.reflect.Field
import java.time.LocalDate


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

                            if (field.type == Int::class.java) {
                                val textField = TextField()
                                textField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)
                                textField.valueChangeMode = ValueChangeMode.EAGER
                                binder
                                        .forField(textField)
                                        .withConverter(StringToIntegerConverter("Invalid Integer"))
                                        .bind(prefix + field.name)

                                formLayout.addFormItem(textField, field.name.capitalize())
                            }else if(field.type == LocalDate::class.java){
                                val dateField = DatePicker()
                                dateField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)

                                binder.forField(dateField).bind( prefix+field.name )

                                formLayout.addFormItem(dateField, field.name.capitalize())

                            }else {
                                val textField = TextField()
                                textField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)
                                textField.valueChangeMode = ValueChangeMode.EAGER
                                binder
                                        .forField(textField)
                                        .bind(prefix + field.name)

                                formLayout.addFormItem(textField, field.name.capitalize())
                            }

                        }
                        DisplayTypeClasif.OBJECT -> {

                            val objectField = ObjectField(annotation.type.java, null ,verticalLayout,!editable)

                            binder.forField(objectField).bind(prefix + field.name)
                            formLayout.addFormItem(objectField,"")
                        }

                        DisplayTypeClasif.COMPOSITE -> {
                            generateReflectedFields(field.type.declaredFields, field.name + ".")
                        }
                        DisplayTypeClasif.LIST -> {
                            if (verbose) {
                                val listType = annotation.type.java
                                val tableField = TableField(listType, !editable, field.name.capitalize(), verticalLayout)


                                binder.forField(tableField).bind(prefix + field.name)
                                formLayout.addFormItem(tableField, "")
                            }

                        }
                    }

                }
            }
        }
    }

}
