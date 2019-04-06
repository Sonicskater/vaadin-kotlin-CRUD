package com.cpsc471.tms.data.types

import com.cpsc471.tms.ui.components.Display
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class School (
        @Display
    var gradeMin:Int,
        @Display
    var gradeMax:Int,
    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects: MutableList<Project>,

    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    var applicationSchools: MutableList<SchoolGrantApplication>


) : Institute()
