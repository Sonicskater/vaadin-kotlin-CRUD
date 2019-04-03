package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
class InvoiceItem(
        @Id
        @ManyToOne(targetEntity = Invoice::class)
        var invoice: Invoice,
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        var item_number: Int,
        var description: String,
        var amount: Int
) : DBAbstract(), Serializable {
    override fun IDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}