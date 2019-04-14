package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.contactContactInfos.ContactContactInfo
import com.cpsc471.tms.data.repository.contactContactInfos.ContactContactInfoRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("contact-details", layout = BaseAppLayout::class)
class ContactDetailsView(@Autowired repo: ContactContactInfoRepository) : VerticalLayout() {


    init {
        add(CrudPage(ContactContactInfo::class.java,repo))

    }

}
