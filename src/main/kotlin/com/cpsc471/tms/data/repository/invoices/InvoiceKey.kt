package com.cpsc471.tms.data.repository.invoices

import com.cpsc471.tms.data.repository.DBKey
import javax.persistence.*

@Embeddable
class InvoiceKey : DBKey() {

    @GeneratedValue
    var id : Int = 0

    override fun iDforDb(): List<Any?> {
        return listOf(id)
    }

}