package com.cpsc471.model.types

import org.apache.commons.lang3.mutable.Mutable
import java.io.Serializable
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class School (
    var Grade_min:Int,
    var Grade_max:Int,
    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects: MutableList<Project>,

    @OneToMany(targetEntity = GrantApplication::class, mappedBy = "applicant")
    var applications: MutableList<GrantApplication>


) : Institute()
