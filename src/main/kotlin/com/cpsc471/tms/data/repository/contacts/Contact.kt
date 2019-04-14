package com.cpsc471.tms.data.repository.contacts


import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.contactContactInfos.ContactContactInfo
import com.cpsc471.tms.ui.crudpages.ContactsView
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "contact")
class Contact : DBAbstract(), Serializable{
    override fun view(ui: UI) {
        ui.navigate(ContactsView::class.java)
    }

    @EmbeddedId
    @Display(clasif = DisplayTypeClasif.COMPOSITE)
    var contactKey: ContactKey = ContactKey()

    @Display
    var firstName: String? = null

    @Display
    var lastName: String? = null

    @Display
    var description: String? = null

    @Display(DisplayTypeClasif.LIST, type = ContactContactInfo::class)
    @OneToMany(targetEntity = ContactContactInfo::class, mappedBy = "contactContactInfoKey.contact")
    var contactInfo: MutableList<ContactContactInfo> = mutableListOf()

    override fun delete() {
        RepoHelper.contactRepository.deleteById(contactKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { t, _ ->
            val t2 = t as Contact
            if (RepoHelper.contactRepository.existsById(t2.contactKey)){
                ValidationResult.error("")
            }else{
                ValidationResult.ok()
            }
        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.contactRepository as CrudRepository<T, ID>
    }

    override fun keyType(): Class<out DBKey> {
        return ContactKey::class.java
    }

    override fun iDforDb() : List<Any> {
        return listOf(contactKey)
    }

}