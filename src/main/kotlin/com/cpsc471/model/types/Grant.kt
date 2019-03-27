package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
@Entity
class Grant(
        @Id
        var name: String,
        @Id
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource
) :DBAbstract(), Serializable {
    override fun getID(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}