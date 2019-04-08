package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.types.FundingSource
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.components.DisplayOld
import com.cpsc471.tms.ui.components.Editable
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SchoolGrantApplicationKey(

        @DisplayOld
        @Editable(required = true)
        var name: String,

        @DisplayOld
        @Editable(required = true,primitive = false)
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource,

        @DisplayOld
        @Editable(required = true, primitive = false)
        @ManyToOne(targetEntity = School::class)
        var applicant: School
) {
}