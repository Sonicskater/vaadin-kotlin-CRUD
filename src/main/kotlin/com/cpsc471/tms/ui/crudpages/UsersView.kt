package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.users.User
import com.cpsc471.tms.data.repository.users.UserRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("users", layout = BaseAppLayout::class)
class UsersView(@Autowired repo: UserRepository) : VerticalLayout() {


    init {

        add(CrudPage(User::class.java,repo, editable = false))

    }

}
