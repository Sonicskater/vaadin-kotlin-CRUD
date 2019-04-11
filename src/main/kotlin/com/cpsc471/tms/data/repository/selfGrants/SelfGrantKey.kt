package com.cpsc471.tms.data.repository.selfGrants

import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.FundingSource
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class SelfGrantKey : DBKey(){
    override fun iDforDb(): List<Any?> {
        return listOf(name, source)
    }

    var name: String? = null

    @ManyToOne(targetEntity = FundingSource::class)
    var source: FundingSource? = null
}
