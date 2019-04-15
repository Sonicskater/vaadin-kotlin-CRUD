package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import java.time.LocalDate

class DateFieldUI(val df :DateField, editable: Boolean) : VerticalLayout() {

    private val grid = Grid<DateContainer>(DateContainer::class.java)
    var ls = mutableListOf<DateContainer>()
    val horizontalLayout = HorizontalLayout()
    init {
        add(grid)

        update()
        if(editable) {
            add(horizontalLayout)
            val datePicker = DatePicker("Date to Add")
            horizontalLayout.add(datePicker)
            horizontalLayout.add(Button("Add Date") {
                df.backingField.add(datePicker.value)
                println(df.backingField)
                update()
            })
            horizontalLayout.add(Button("Remove Date") {
                df.backingField.remove(grid.selectionModel.selectedItems.first().date)
                update()
            })
        }
    }

    fun update(){
        ls = mutableListOf()
        for (date  in df.backingField){
            ls.add(DateContainer(date))
        }
        grid.setItems(ls)
    }

    fun save(){
        val ls2 = mutableListOf<LocalDate>()
        for (date  in ls){
            ls2.add(date.date)
        }
        df.backingField = ls2
    }



    class DateContainer(var date : LocalDate)

}