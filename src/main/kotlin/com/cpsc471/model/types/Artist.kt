package com.cpsc471.model.types

import java.sql.Date
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class Artist(
        @ManyToMany(targetEntity = Project::class, mappedBy = "members")
        var projects: List<Project> = mutableListOf(),

        @Basic
        @ElementCollection
        var available_days: MutableSet<Date> = mutableSetOf(),

        @Basic
        @ElementCollection
        var notes: MutableList<String>
) : User() {
}