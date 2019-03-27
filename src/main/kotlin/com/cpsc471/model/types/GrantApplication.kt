package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class GrantApplication(
    @Id
    var name: String,
    var status: String,
    var amonut: Int,
    var website: String,

    @Id
    @ManyToOne(targetEntity = FundingSource::class)
    var source: FundingSource,
    @Id
    @ManyToOne(targetEntity = School::class)
    var applicant: School

) : DBAbstract(), Serializable{
    override fun getID(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}