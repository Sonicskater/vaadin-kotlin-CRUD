package com.cpsc471.tms.ui

import com.vaadin.flow.component.Text
import com.vaadin.flow.component.orderedlayout.*
import com.vaadin.flow.router.Route

@Route("new",layout = BaseAppLayout::class)
class NewView : VerticalLayout() {
    init {
        add(Text("NEWVIEW 1"))

    }
}
