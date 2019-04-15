package com.cpsc471.tms.data.repository.selfGrants

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.FundingSource
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SelfGrantKey : DBKey(){
    override fun iDforDb(): List<Any?> {
        return listOf(name, source)
    }
    @Display( editLevel = DisplayEditLevel.CREATABLE)
    var name: String? = null

    @Display(DisplayTypeClasif.OBJECT, editLevel = DisplayEditLevel.CREATABLE, type = FundingSource::class)
    @ManyToOne(targetEntity = FundingSource::class)
    var source: FundingSource? = null
}
