package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "school")
class School (
    var Grade_min:Int,
    var Grade_max:Int,

    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects:List<Project>
) : Serializable, Institute()
