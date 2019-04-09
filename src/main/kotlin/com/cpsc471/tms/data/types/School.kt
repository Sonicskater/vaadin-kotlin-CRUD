package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.ui.components.DisplayOld
import com.cpsc471.tms.ui.components.DisplayList
import com.cpsc471.tms.ui.components.Editable
import com.cpsc471.tms.ui.components.EditableList
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
@Configurable(dependencyCheck = true)
open class School (
        @Editable
        @DisplayOld
        @Display
    open var gradeMin:Int = 0,

        @Editable
        @DisplayOld
        @Display
    open var gradeMax:Int = 0,

        @EditableList(Project::class)
        @DisplayList(Project::class)
        @Display(type = Project::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = Project::class, mappedBy = "projectKey.school")
    open var projects: MutableList<Project> = mutableListOf(),

        @EditableList(SchoolGrantApplication::class)
        @DisplayList(SchoolGrantApplication::class)
        @Display(type = SchoolGrantApplication::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    open var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()


) : Institute() {

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.schoolRepository as CrudRepository<T, ID>
    }

}
