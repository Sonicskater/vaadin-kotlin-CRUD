package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.types.Project
import javax.persistence.*

@Embeddable
class InvoiceKey : DBKey() {

    @GeneratedValue
    var id : Int = 0

    override fun iDforDb(): List<Any?> {
        return listOf(id)
    }

}