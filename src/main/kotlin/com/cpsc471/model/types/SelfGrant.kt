package com.cpsc471.model.types

import java.io.Serializable
import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
@Entity
class SelfGrant(
        @Id
        var name: String,
        var status: String,
        var amonut: Int,
        var website: String,
        var submissionDate: Date,

        @Id
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource
) :DBAbstract(), Serializable {
    override fun getID(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}