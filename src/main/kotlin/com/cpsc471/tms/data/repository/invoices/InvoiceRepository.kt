package com.cpsc471.tms.data.repository.invoices

import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, InvoiceKey> {
}