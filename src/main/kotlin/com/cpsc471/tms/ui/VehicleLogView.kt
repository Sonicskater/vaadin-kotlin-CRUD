package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.vehicles.Vehicle
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route


@Route("vehicle-logs", layout = BaseAppLayout::class)
class VehicleLogView : VerticalLayout() {
    val dbObjectList = DBObjectList(Vehicle::class.java)
    init {
        add(dbObjectList)
        val hz = HorizontalLayout()
        add(hz)
        val numberField = NumberField("Mileage")
        hz.add(numberField)
        hz.add(Button("Add Mileage"){
            var base = dbObjectList.selected()?.mileage ?: 0
            dbObjectList.selected()?.let{
                it.mileage = base + numberField.value.toInt()
            RepoHelper.vehicleRepository.save(it)
            update()}
        })
        val noteField = TextField("Note")
        hz.add(noteField)
        hz.add(Button("Add Mileage"){
            dbObjectList.selected()?.let{
                it.notes.add(noteField.value)
                RepoHelper.vehicleRepository.save(it)
                update()}
        })
        update()
    }
    fun update(){
        dbObjectList.setItems(RepoHelper.vehicleRepository.findAll() as Collection<Vehicle>)
    }
}
