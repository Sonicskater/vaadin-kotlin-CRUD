package com.cpsc471.tms.data.repository.invoiceItems

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class InvoiceItem : DBAbstract(), Serializable {
    override fun view(ui: UI) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @EmbeddedId
    var invoiceItemKey : InvoiceItemKey = InvoiceItemKey()

    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var description: String? = null

    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var amount: Int = 0

    override fun delete() {
        RepoHelper.invoiceItemRepository.deleteById(this.invoiceItemKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { t, _ ->
            val t2 = t as InvoiceItem
            when {
                RepoHelper.invoiceItemRepository.existsById(t2.invoiceItemKey) -> ValidationResult.error("")
                else -> ValidationResult.ok()
            }
        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.invoiceItemRepository as CrudRepository<T, ID>
    }

    override fun keyType(): Class<out DBKey> {
        return InvoiceItemKey::class.java
    }

    override fun iDforDb(): List<Any> {
        return listOf(invoiceItemKey)
    }
}