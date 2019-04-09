package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.keys.DBKey
import org.springframework.data.repository.CrudRepository
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
    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKeyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}