package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.InvoiceKey
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
class Invoice: DBAbstract(), Serializable {


    @EmbeddedId
    var invoiceKey: InvoiceKey = InvoiceKey()


    @OneToOne(fetch = FetchType.LAZY,mappedBy = "invoice", targetEntity = Project::class)
    var project: Project? = null

    @OneToMany(targetEntity = InvoiceItem::class,mappedBy = "invoice")
    var items: MutableList<InvoiceItem> = mutableListOf()

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}