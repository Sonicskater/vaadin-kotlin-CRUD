package com.cpsc471.tms.data.types

import java.io.Serializable
import javax.persistence.*

@Entity
class Invoice(
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private var invoiceID: Int,
        @OneToOne(mappedBy = "invoice",fetch = FetchType.LAZY)
        var project: Project,
        @OneToMany(targetEntity = InvoiceItem::class,mappedBy = "invoice")
        var items: MutableList<InvoiceItem>
) : DBAbstract(), Serializable {
    override fun IDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}