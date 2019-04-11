package com.cpsc471.tms.data.repository.contacts

import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, ContactKey> {
}