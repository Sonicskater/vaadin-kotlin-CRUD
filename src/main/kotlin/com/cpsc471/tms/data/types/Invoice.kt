package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.keys.DBKey
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
@Configurable(dependencyCheck = true)
class Invoice(
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private var invoiceID: Int = 0,
        @OneToOne(mappedBy = "invoice",fetch = FetchType.LAZY)
        var project: Project? = null,
        @OneToMany(targetEntity = InvoiceItem::class,mappedBy = "invoice")
        var items: MutableList<InvoiceItem> = mutableListOf()
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