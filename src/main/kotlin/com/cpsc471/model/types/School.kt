package com.cpsc471.model.types

import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class School (
    var Grade_min:Int,
    var Grade_max:Int,
    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects: MutableList<Project>,

    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    var applicationSchools: MutableList<SchoolGrantApplication>


) : Institute()
