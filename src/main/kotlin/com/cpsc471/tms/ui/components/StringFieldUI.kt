package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField

class StringFieldUI(val df :StringField, editable: Boolean) : VerticalLayout() {

    private val grid = Grid<StringContainer>(StringContainer::class.java)
    var ls = mutableListOf<StringContainer>()
    val horizontalLayout = HorizontalLayout()
    init {
        add(grid)

        update()
        if(editable) {
            add(horizontalLayout)
            val datePicker = TextField("Note to Add")
            horizontalLayout.add(datePicker)
            horizontalLayout.add(Button("Add Note") {
                df.backingField.add(datePicker.value)
                println(df.backingField)
                update()
            })
            horizontalLayout.add(Button("Remove Note") {
                df.backingField.remove(grid.selectionModel.selectedItems.first().Note)
                update()
            })
        }
    }

    fun update(){
        ls = mutableListOf()
        for (date  in df.backingField){
            ls.add(StringContainer(date))
        }
        grid.setItems(ls)
    }

    fun save(){
        val ls2 = mutableListOf<String>()
        for (date  in ls){
            ls2.add(date.Note)
        }
        df.backingField = ls2
    }



    class StringContainer(var Note : String)

}