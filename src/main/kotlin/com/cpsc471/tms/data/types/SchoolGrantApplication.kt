package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.Display
import java.io.Serializable
import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class SchoolGrantApplication(
    @Id
    @Display
    var name: String,
    @Display
    var status: String,
    @Display
    var amonut: Int,
    var website: String,
    var submissionDate: Date,

    @Id
    @ManyToOne(targetEntity = FundingSource::class)
    var source: FundingSource,

    @Id
    @ManyToOne(targetEntity = School::class)
    var applicant: School

) : DBAbstract(), Serializable{
    override fun IDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}