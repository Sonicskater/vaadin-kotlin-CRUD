package com.cpsc471.tms.data.types


import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.ContactKey
import com.cpsc471.tms.data.keys.DBKey
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "contact")
open class Contact(
        @EmbeddedId
        @Display(clasif = DisplayTypeClasif.COMPOSITE)
        var contactKey: ContactKey = ContactKey(),

        @Display
        var firstName: String = "",

        var lastName: String = "",

        var description: String = "",

        @OneToMany(targetEntity = ContactContactInfo::class, mappedBy = "contact")
        var contactInfo: MutableList<ContactContactInfo> = mutableListOf()

        ) : DBAbstract(), Serializable{
    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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