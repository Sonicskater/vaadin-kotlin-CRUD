package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name= "contact_contact_info")
class ContactContactInfo(
        @Id
        var Phone_number: String,
        var Description: String,

        @Id
        @ManyToOne(fetch = FetchType.LAZY,targetEntity = Contact::class)
        var contact: Contact
) : DBAbstract(),Serializable{
    override fun getID(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}