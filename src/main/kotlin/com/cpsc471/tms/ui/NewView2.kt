package com.cpsc471.tms.ui

import com.vaadin.flow.component.Text
import com.vaadin.flow.component.orderedlayout.*
import com.vaadin.flow.router.Route

@Deprecated("testing class")
@Route("new2",layout = BaseAppLayout::class)
class NewView2 :VerticalLayout() {
    init {
        add(Text("NEWVIEW 2"))
    }
}
