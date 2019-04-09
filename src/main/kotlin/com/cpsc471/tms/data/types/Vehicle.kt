package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.keys.DBKey
import org.springframework.data.repository.CrudRepository
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
    override fun getKeyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        return listOf(license_plate)
    }

}