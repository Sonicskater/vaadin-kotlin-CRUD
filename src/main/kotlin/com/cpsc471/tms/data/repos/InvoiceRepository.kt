package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.InvoiceKey
import com.cpsc471.tms.data.types.Invoice
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, InvoiceKey> {
}