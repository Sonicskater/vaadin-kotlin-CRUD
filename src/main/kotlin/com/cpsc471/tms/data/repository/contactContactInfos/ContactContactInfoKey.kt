package com.cpsc471.tms.data.repository.contactContactInfos

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.contacts.Contact
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class ContactContactInfoKey : DBKey(){
    override fun iDforDb(): List<Any?> {
        return listOf(phoneNumber,contact)
    }

    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var phoneNumber: String? = null

    @Display(DisplayTypeClasif.OBJECT, editLevel = DisplayEditLevel.CREATABLE, type = Contact::class)
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Contact::class)
    var contact: Contact? = null
}