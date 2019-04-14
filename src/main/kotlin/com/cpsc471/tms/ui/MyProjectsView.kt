package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.getUser
import com.cpsc471.tms.hasRole
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("my-projects", layout = BaseAppLayout::class)
class MyProjectsView : VerticalLayout(){
    val dbObjectList = DBObjectList(Project::class.java)
    init {
        val principal = getUser()


        val list = RepoHelper.projectRepository.findAll()
        when {
            hasRole("ROLE_ARTIST") -> {
                var artist = principal as Artist
                list.removeAll {
                    project -> artist !in project.members
                }
            }

            hasRole("ROLE_MANAGER") -> {
                var manager = principal as Manager
                list.removeAll {
                    project -> manager != project.manager
                }
            }
        }
        dbObjectList.setItems(list as Collection<Project>)

        add(dbObjectList)
    }
}
