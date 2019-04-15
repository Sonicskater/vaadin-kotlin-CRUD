package com.cpsc471.tms.data.repository.vehicles

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.ui.crudpages.VehicleView
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

@Entity
class Vehicle(
        @EmbeddedId
    @Display(DisplayTypeClasif.COMPOSITE)
    var vehicleKey: VehicleKey = VehicleKey(),

        @Display
        var description: String = "",

        @Display
        var mileage:Int = 0,
    //var notes: List<VehicleNote>

        @Display(DisplayTypeClasif.LIST, type = Project::class)
        @OneToMany(targetEntity = Project::class, mappedBy = "vehicle")
    var projects: MutableList<Project> = mutableListOf(),


        @Display(DisplayTypeClasif.LIST, type_other = String::class)
        @Basic
    @ElementCollection
    var notes: MutableList<String> = mutableListOf()

): DBAbstract(){
    override fun view(ui: UI) {
        ui.navigate(VehicleView::class.java)
    }

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