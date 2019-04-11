package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.institute.FundingSource
import com.cpsc471.tms.data.repository.institute.FundingSourceRepository
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("funding-sources", layout = BaseAppLayout::class)
class FundingSourceView(@Autowired repo : FundingSourceRepository) : VerticalLayout(){

    init {
        add(CrudPage(FundingSource::class.java,repo ))
    }
}
