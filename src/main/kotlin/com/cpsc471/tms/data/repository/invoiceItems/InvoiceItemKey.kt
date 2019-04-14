package com.cpsc471.tms.data.repository.invoiceItems

import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.invoices.Invoice
import javax.persistence.Embeddable
import javax.persistence.ManyToOne

@Embeddable
class InvoiceItemKey : DBKey() {
    override fun iDforDb(): List<Any?> {
        return listOf(invoice, itemNumber)
    }


    @ManyToOne(targetEntity = Invoice::class)
    var invoice: Invoice? = null

    var itemNumber: Int = 0
}

