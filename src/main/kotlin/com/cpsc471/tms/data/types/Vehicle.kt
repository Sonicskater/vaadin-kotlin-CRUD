package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.VehicleKey
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
        RepoHelper.vehicleRepositry
    }

    override fun <T> getValidator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { value, context ->
            if (value is Vehicle){
                when{
                    RepoHelper.vehicleRepositry.existsById(vehicleKey) && creation -> ValidationResult.error("Already exists")

                    value == Vehicle() -> ValidationResult.error("Default object")

                    else -> ValidationResult.ok()
                }
            }else{
                ValidationResult.error("Not a vehicle")
            }
        }
    }

    override fun getKeyType(): Class<out DBKey> {
        return VehicleKey::class.java
    }

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.vehicleRepositry as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(vehicleKey)
    }

}