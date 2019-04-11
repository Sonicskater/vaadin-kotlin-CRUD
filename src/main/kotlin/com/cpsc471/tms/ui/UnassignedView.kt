package com.cpsc471.tms.ui

import com.cpsc471.tms.data.repository.users.ArtistRepository
import com.cpsc471.tms.data.repository.users.ManagerRepository
import com.cpsc471.tms.data.repository.users.UserRepository
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("unassigned", layout = BaseAppLayout::class)
class UnassignedView(@Autowired artistRepository:  ArtistRepository ,
                     @Autowired managerRepository: ManagerRepository,
                     @Autowired userRepository: UserRepository) : VerticalLayout() {


    init {


    }

}
