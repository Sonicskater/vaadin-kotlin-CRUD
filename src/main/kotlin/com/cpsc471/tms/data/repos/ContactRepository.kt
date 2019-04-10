package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.ContactKey
import com.cpsc471.tms.data.types.Contact
import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, ContactKey> {
}