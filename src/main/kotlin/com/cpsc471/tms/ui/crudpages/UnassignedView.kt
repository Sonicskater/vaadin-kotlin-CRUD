package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.users.*
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("unassigned", layout = BaseAppLayout::class)
class UnassignedView(@Autowired val artistRepository:  ArtistRepository ,
                     @Autowired val managerRepository: ManagerRepository,
                     @Autowired val userRepository: UserRepository) : VerticalLayout() {

    var dbObjectList = DBObjectList(User::class.java)
    var horizontalLayout = HorizontalLayout()
    init {
        add(dbObjectList)
        add(horizontalLayout)
        horizontalLayout.add(Button("Convert to Artist", VaadinIcon.PAINTBRUSH.create()){
            val user = dbObjectList.selectionModel.selectedItems.firstOrNull()
            if (user !=null) {
                artistRepository.save(Artist(user))
            }
            update()
        })

        horizontalLayout.add(Button("Convert to Manager", VaadinIcon.SUITCASE.create()){
            val user = dbObjectList.selectionModel.selectedItems.firstOrNull()
            if (user !=null) {
                managerRepository.save(Manager(user))
            }
            update()
        })

        update()
    }

    private fun update(){
        val list : MutableList<User> = userRepository.findAll() as MutableList<User>

        list.removeAll(artistRepository.findAll())
        list.removeAll(managerRepository.findAll())

        dbObjectList.setItems(list)
    }

}
