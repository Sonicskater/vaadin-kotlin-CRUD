package com.cpsc471.model.types

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Vehicle(
    @Id var license_plate: String = "",
    var description: String,
    var mileage:Int,
    var notes: List<VehicleNote>

): DBAbstract(){
    override fun getID(): List<Any> {
        return listOf(license_plate)
    }

}