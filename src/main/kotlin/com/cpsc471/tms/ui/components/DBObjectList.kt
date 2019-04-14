package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.vaadin.flow.component.grid.Grid
import java.lang.reflect.Field

class DBObjectList< T: DBAbstract>(
        private val classT : Class<T>
) : Grid<T>(classT, false){
    init {
        setSelectionMode(SelectionMode.SINGLE)
    }
    override fun setItems(list : Collection<T>){
        if (list is MutableList)
        {
            val list2 = list.filter {
                it != classT.newInstance()
            }
            super.setItems(list2)
        }
        render()
    }

    fun selected(): T?{
        return super.getSelectedItems().firstOrNull()
    }

    private fun render(){
        removeAllColumns()
        generateReflectedFields(classT.superclass.declaredFields)
        generateReflectedFields(classT.declaredFields)
    }

    private fun generateReflectedFields(fields: Array<Field>, prefix: String = ""){
        for ( field: Field in fields){
            val annotation = field.getAnnotation(Display::class.java)
            if (annotation != null){
                when(annotation.clasif){

                    DisplayTypeClasif.PRIMITIVE ->{
                        addColumn(prefix + field.name)
                    }
                    DisplayTypeClasif.OBJECT -> {
                        //generateReflectedFields(field.type.superclass.declaredFields,prefix + field.name+".")
                        //generateReflectedFields(field.type.declaredFields, prefix + field.name+".")
                    }

                    DisplayTypeClasif.COMPOSITE -> {
                        generateReflectedFields(field.type.declaredFields, prefix + field.name+".")
                    }
                    DisplayTypeClasif.LIST -> {
                    }
                }

            }
        }
    }
}