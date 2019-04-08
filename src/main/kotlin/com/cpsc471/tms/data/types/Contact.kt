package com.cpsc471.tms.data.types


import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.keys.ContactKey
import com.cpsc471.tms.ui.components.*
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "contact")
class Contact(
        @DisplayOld
        @Editable
        var firstName: String,

        @DisplayOld
        @Editable
        var lastName: String,

        @DisplayDetail
        @Editable
        var description: String,

        @EmbeddedId
        @EditableComposite
        @DisplayComposite
        var contactKey: ContactKey,

        @DisplayList(ContactContactInfo::class)
        @OneToMany(targetEntity = ContactContactInfo::class, mappedBy = "contact")
        var contactInfo: List<ContactContactInfo>

        ) : DBAbstract(), Serializable{
    override fun IDforDb() : List<Any> {
        return listOf(contactKey)
    }

}