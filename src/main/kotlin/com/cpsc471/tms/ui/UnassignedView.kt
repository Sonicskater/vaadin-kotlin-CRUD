package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.users.*
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("unassigned", layout = BaseAppLayout::class)
class UnassignedView(@Autowired artistRepository:  ArtistRepository ,
                     @Autowired managerRepository: ManagerRepository,
                     @Autowired userRepository: UserRepository) : VerticalLayout() {

    var dbObjectList = DBObjectList(User::class.java)
    var horizontalLayout = HorizontalLayout()
    init {
        add(dbObjectList)
        add(horizontalLayout)
        horizontalLayout.add(Button("Convert to Artist", VaadinIcon.PAINTBRUSH.create()){
            val user = dbObjectList.selectionModel.selectedItems.firstOrNull()
            if (user !=null) {
                RepoHelper.artistRepository.save(Artist(user))
            }
            update()
        })

        horizontalLayout.add(Button("Convert to Manager", VaadinIcon.SUITCASE.create()){
            val user = dbObjectList.selectionModel.selectedItems.firstOrNull()
            if (user !=null) {
                RepoHelper.managerRepository.save(Manager(user))
            }
            update()
        })

        update()
    }

    private fun update(){
        val list : MutableList<User> = RepoHelper.userRepository.findAll() as MutableList<User>

        list.removeAll(RepoHelper.artistRepository.findAll())
        list.removeAll(RepoHelper.managerRepository.findAll())

        dbObjectList.setItems(list)
    }

}
