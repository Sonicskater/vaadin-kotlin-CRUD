package com.cpsc471.tms.data.repository.invoiceItems

import org.springframework.data.repository.CrudRepository

interface InvoiceItemRepository : CrudRepository<InvoiceItem, InvoiceItemKey> {
}