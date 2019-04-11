package com.cpsc471.tms.data.repository.schoolGrantApplications

import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.FundingSource
import com.cpsc471.tms.data.repository.institute.School
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SchoolGrantApplicationKey: DBKey(){
        override fun iDforDb(): List<Any?> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        var name: String? = null

        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource? = null

        @ManyToOne(targetEntity = School::class)
        var applicant: School? = null
}