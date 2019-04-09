package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.types.FundingSource
import com.cpsc471.tms.data.types.School
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SchoolGrantApplicationKey(

        var name: String,

        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource,

        @ManyToOne(targetEntity = School::class)
        var applicant: School
)