package com.cpsc471.tms.data.repository.vehicles

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.data.repository.DBAbstract
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

@Entity
class Vehicle(
        @EmbeddedId
    @Display(DisplayTypeClasif.COMPOSITE)
    var vehicleKey: VehicleKey = VehicleKey(),
        var description: String = "",
        var mileage:Int = 0,
    //var notes: List<VehicleNote>

        @OneToMany(targetEntity = Project::class, mappedBy = "vehicle")
    var projects: MutableList<Project> = mutableListOf(),

        @Basic
    @ElementCollection
    var notes: MutableList<String> = mutableListOf()

): DBAbstract(){
    override fun delete() {
        RepoHelper.vehicleRepository
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { value, context ->
            if (value is Vehicle){
                when{
                    RepoHelper.vehicleRepository.existsById(vehicleKey) && creation -> ValidationResult.error("Already exists")

                    value == Vehicle() -> ValidationResult.error("Default object")

                    else -> ValidationResult.ok()
                }
            }else{
                ValidationResult.error("Not a vehicle")
            }
        }
    }

    override fun keyType(): Class<out DBKey> {
        return VehicleKey::class.java
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.vehicleRepository as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(vehicleKey)
    }

}