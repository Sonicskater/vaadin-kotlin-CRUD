package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.SecurityHelper
import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.data.repository.users.User
import com.cpsc471.tms.hasRole
import com.cpsc471.tms.ui.components.DBObjectForm
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route


@Route("me", layout = BaseAppLayout::class)
class MeView : VerticalLayout() {
    private val dbObjectForm : DBObjectForm<out User>
    init {
        if (hasRole("ROLE_ARTIST")){
            dbObjectForm = DBObjectForm(Artist::class.java,editable = true)
            dbObjectForm.setObject(RepoHelper.artistRepository.findArtistByUserKeyEmail(SecurityHelper.principal.username))
        }else{
            dbObjectForm = DBObjectForm(Manager::class.java,editable = true)
            dbObjectForm.setObject(RepoHelper.managerRepository.findByUserKeyEmail(SecurityHelper.principal.username))
        }

        dbObjectForm.render()
        add(dbObjectForm)

        add(Button("Save Changes"){
            dbObjectForm.save()
            val user = dbObjectForm.getObject()
            if (user is Artist){
                RepoHelper.artistRepository.save(user)
            }else if (user is Manager){
                RepoHelper.managerRepository.save(user)
            }
        })


    }
}