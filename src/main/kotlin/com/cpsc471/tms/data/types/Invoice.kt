package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.keys.DBKey
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
class Invoice(
        @Id
        @OneToOne(fetch = FetchType.LAZY,mappedBy = "project", targetEntity = Project::class)
        var project: Project? = null,

        @OneToMany(targetEntity = InvoiceItem::class,mappedBy = "invoice")
        var items: MutableList<InvoiceItem> = mutableListOf()
) : DBAbstract(), Serializable {
    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> getValidator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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