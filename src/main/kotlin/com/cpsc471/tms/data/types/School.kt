package com.cpsc471.tms.data.types

import com.cpsc471.tms.ui.components.Display
import com.cpsc471.tms.ui.components.DisplayList
import com.cpsc471.tms.ui.components.Editable
import com.cpsc471.tms.ui.components.EditableList
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
open class School (
        @Editable
        @Display
    open var gradeMin:Int = 0,

        @Editable
        @Display
    open var gradeMax:Int = 0,

        @EditableList(Project::class)
        @DisplayList(Project::class)
    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    open var projects: MutableList<Project> = mutableListOf(),

        @EditableList(SchoolGrantApplication::class)
        @DisplayList(SchoolGrantApplication::class)
    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    open var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()


) : Institute()
