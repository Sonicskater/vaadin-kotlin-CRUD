package com.cpsc471.model.types

import javax.persistence.Id

class VehicleNote(
        @Id var note:String,
        @Id var vehicle:Vehicle
) : DBAbstract() {
    override fun getID(): List<Any> {
        return listOf()
    }
}