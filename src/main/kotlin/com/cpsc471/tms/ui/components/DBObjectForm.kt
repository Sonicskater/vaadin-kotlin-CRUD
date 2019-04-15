package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.invoiceItems.InvoiceItem
import com.cpsc471.tms.data.repository.invoices.Invoice
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.data.binder.ValidationException
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.converter.StringToIntegerConverter
import com.vaadin.flow.data.value.ValueChangeMode
import org.springframework.dao.DataIntegrityViolationException
import java.lang.reflect.Field
import java.time.LocalDate


class DBObjectForm<T : DBAbstract>(
        private val classT: Class<T>,
        val editable: Boolean = false,
        private val creatable: Boolean = false,
        var verbose: Boolean = true,
        var lists: Boolean = true
) : VerticalLayout() {
    private val formLayout : FormLayout = FormLayout()
    private var binder : Binder<T> = Binder(classT)

    private var item: T? = null

    private val verticalLayout = VerticalLayout()

    private var label = Label()
    init {
        add(formLayout)
        add(verticalLayout)
        if(creatable) {
            binder.withValidator(classT.newInstance().validator(classT, creatable))
        }
        binder.setStatusLabel(label)

    }


    fun setObject(item : T?) : DBObjectForm<T> {
        this.item = item
        return this
    }
    fun save(){

        try {
            if (binder.writeBeanIfValid(item)){
                Notification("Success", 3000).open()
                println("Saved")
            }else{
                Notification("Failure", 3000).open()
                println("Failed to save")
            }
        }catch( e : ValidationException){

            try {
                item?.delete()
            }catch (e2 : DataIntegrityViolationException){

            }
            for (em: ValidationResult in e.validationErrors ){
                println(em.errorMessage)
            }
        }

    }

    fun getObject() : T?{
        return item
    }

    fun clear(){
        formLayout.removeAll()
    }


    fun render() {
        println("Rendering form for ${classT.simpleName}")
        if(item != null){
            println("object is valid")
            formLayout.removeAll()
            verticalLayout.removeAll()
            generateReflectedFields(classT.superclass.declaredFields)
            generateReflectedFields(classT.declaredFields)
            binder.readBean(item)
        }else{
            formLayout.removeAll()
            verticalLayout.removeAll()
            verticalLayout.add("No ${classT.simpleName} selected")
        }
    }

    private fun generateReflectedFields(fields: Array<Field>, prefix: String = ""){
        for ( field: Field in fields){
            println(field.name)
            println(field.type
            )
            val annotation = field.getAnnotation(Display::class.java)
            if (annotation != null) {
                if (annotation.category != DisplayCategory.VERBOSE || verbose) {
                    when (annotation.clasif) {
                        DisplayTypeClasif.PRIMITIVE -> {
                            println("adding primitive")
                            when {
                                field.type == Int::class.java -> {
                                    val textField = TextField()
                                    textField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)
                                    textField.valueChangeMode = ValueChangeMode.EAGER
                                    binder
                                            .forField(textField)
                                            .withConverter(StringToIntegerConverter("Invalid Integer"))
                                            .bind(prefix + field.name)

                                    formLayout.addFormItem(textField, field.name.capitalize())
                                }
                                field.type == LocalDate::class.java -> {
                                    val dateField = DatePicker()
                                    dateField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)

                                    binder.forField(dateField).bind( prefix+field.name )

                                    formLayout.addFormItem(dateField, field.name.capitalize())

                                }
                                else -> {
                                    val textField = TextField()
                                    textField.isReadOnly = !((annotation.editLevel == DisplayEditLevel.EDITABLE && editable) || creatable)
                                    textField.valueChangeMode = ValueChangeMode.EAGER
                                    binder
                                            .forField(textField)
                                            .bind(prefix + field.name)

                                    formLayout.addFormItem(textField, field.name.capitalize())
                                }
                            }

                        }
                        DisplayTypeClasif.OBJECT -> {
                            if (annotation.type == Invoice::class){


                            }else {
                                println("adding object")

                                val objectField = ObjectField(annotation.type.java, null, verticalLayout, !editable)

                                binder.forField(objectField).bind(prefix + field.name)
                                formLayout.addFormItem(objectField, "")
                            }

                        }


                        DisplayTypeClasif.COMPOSITE -> {

                            println("adding composite")
                            generateReflectedFields(field.type.declaredFields, field.name + ".")

                        }
                        DisplayTypeClasif.LIST -> {
                            if ((verbose and lists) || annotation.type_other.java == LocalDate::class.java || annotation.type_other.java == String::class.java) {
                                when {
                                    annotation.type_other.java == LocalDate::class.java -> {

                                        val dateField = DateField(verticalLayout,editable)


                                        binder.forField(dateField).bind(prefix + field.name)
                                        formLayout.addFormItem(dateField, "")

                                    }
                                    annotation.type_other.java == String::class.java -> {
                                        val dateField = StringField(verticalLayout,editable)


                                        binder.forField(dateField).bind(prefix + field.name)
                                        formLayout.addFormItem(dateField, "")
                                    }
                                    annotation.type == InvoiceItem::class ->{
                                        println("adding list")
                                        val listType = annotation.type.java
                                        val tableField = TableField(listType,true, field.name.capitalize(), verticalLayout)


                                        binder.forField(tableField).bind(prefix + field.name)
                                        formLayout.addFormItem(tableField, "")
                                    }
                                    else -> {
                                        println("adding list")
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
    }

}
