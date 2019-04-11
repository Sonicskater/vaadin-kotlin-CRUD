package com.cpsc471.tms.data.repository.contactContactInfos

import com.cpsc471.tms.data.repository.contacts.Contact
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToOne

class ContactContactInfoKey {

    @Id
    var phoneNumber: String? = null

    @Id
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Contact::class)
    var contact: Contact? = null
}