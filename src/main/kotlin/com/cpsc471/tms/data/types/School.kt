package com.cpsc471.tms.data.types

import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class School (
    var GradeMin:Int,
    var GradeMax:Int,
    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects: MutableList<Project>,

    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    var applicationSchools: MutableList<SchoolGrantApplication>


) : Institute()
