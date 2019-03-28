package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class Manager(
        @OneToMany(targetEntity = Project::class, mappedBy = "manager")
        var manages: List<Project> = mutableListOf()
): User(), Serializable {

}
