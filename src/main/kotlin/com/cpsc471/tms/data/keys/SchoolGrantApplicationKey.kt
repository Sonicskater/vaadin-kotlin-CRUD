package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.types.FundingSource
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.components.Display
import com.cpsc471.tms.ui.components.Editable
import javax.persistence.Embeddable
import javax.persistence.Id
import javax.persistence.ManyToOne

@Embeddable
class SchoolGrantApplicationKey(

        @Display
        @Editable(required = true)
        var name: String,

        @Display
        @Editable(required = true,primitive = false)
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource,

        @Display
        @Editable(required = true, primitive = false)
        @ManyToOne(targetEntity = School::class)
        var applicant: School
) {
}