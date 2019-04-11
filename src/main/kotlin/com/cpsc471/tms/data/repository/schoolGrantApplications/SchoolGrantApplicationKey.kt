package com.cpsc471.tms.data.repository.schoolGrantApplications

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.FundingSource
import com.cpsc471.tms.data.repository.institute.School
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SchoolGrantApplicationKey: DBKey(){
        override fun iDforDb(): List<Any?> {
                return listOf(name,source,applicant)
        }
        @Display(editLevel = DisplayEditLevel.CREATABLE, clasif = DisplayTypeClasif.OBJECT, type = FundingSource::class)
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource? = null

        @Display(editLevel = DisplayEditLevel.CREATABLE, clasif = DisplayTypeClasif.OBJECT, type = School::class)
        @ManyToOne(targetEntity = School::class)
        var applicant: School? = null

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var name: String? = null


}