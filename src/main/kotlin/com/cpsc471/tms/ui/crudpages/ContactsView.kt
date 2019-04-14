package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.contacts.Contact
import com.cpsc471.tms.data.repository.contacts.ContactRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("contacts", layout = BaseAppLayout::class)
class ContactsView(@Autowired repo: ContactRepository) : VerticalLayout() {


    init {
        add(CrudPage(Contact::class.java,repo))

    }

}
