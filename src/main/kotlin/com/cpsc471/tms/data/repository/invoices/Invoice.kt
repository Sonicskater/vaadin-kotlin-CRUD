package com.cpsc471.tms.data.repository.invoices

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.invoiceItems.InvoiceItem
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.ui.BillingView
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import com.vaadin.flow.data.binder.ValueContext
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
class Invoice: DBAbstract(), Serializable {
    override fun view(ui: UI) {
        ui.navigate(BillingView::class.java)
    }

    @EmbeddedId
    var invoiceKey: InvoiceKey = InvoiceKey()


    @OneToOne(fetch = FetchType.LAZY,mappedBy = "invoice", targetEntity = Project::class)
    var project: Project? = null

    @Display(DisplayTypeClasif.LIST, type = InvoiceItem::class)
    @OneToMany(targetEntity = InvoiceItem::class,mappedBy = "invoiceItemKey.invoice")
    var items: MutableList<InvoiceItem> = mutableListOf()

    override fun delete() {
        RepoHelper.invoiceRepository.deleteById(this.invoiceKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator{ invoice: T, _: ValueContext ->
            if (RepoHelper.invoiceRepository.existsById((invoice as Invoice).invoiceKey)){
                ValidationResult.error("Already exists")
            }else{
                ValidationResult.ok()
            }
        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.invoiceRepository as CrudRepository<T, ID>
    }

    override fun keyType(): Class<out DBKey> {
        return InvoiceKey::class.java
    }

    override fun iDforDb(): List<Any> {
        return listOf(invoiceKey)
    }

    fun sum(): String? {
        var sum = 0
        for (i in items){
            sum+= i.amount
        }
        return sum.toString()
    }
}