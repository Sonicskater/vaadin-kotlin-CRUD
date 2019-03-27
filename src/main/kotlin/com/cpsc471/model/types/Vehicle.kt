package com.cpsc471.model.types

import javax.persistence.*

@Entity
class Vehicle(
    @Id var license_plate: String = "",
    var description: String,
    var mileage:Int,
    //var notes: List<VehicleNote>

    @OneToMany(targetEntity = Project::class, mappedBy = "vehicle")
    var projects: List<Project>,

    @Basic
    @ElementCollection
    var notes: MutableList<String>

): DBAbstract(){
    override fun getID(): List<Any> {
        return listOf(license_plate)
    }

}