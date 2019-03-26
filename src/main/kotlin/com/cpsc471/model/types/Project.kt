package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="projects")
data class Project(
        @Id
        var Date_key: Int,


        @ManyToMany(targetEntity = DateRecord::class)
        @JoinTable(name="takes_place",
                joinColumns = [JoinColumn(name="Date_key")],
                inverseJoinColumns =
                    [
                        JoinColumn(name= "Month"),
                        JoinColumn(name = "Number"),
                        JoinColumn(name = "Year")
                    ]
                )
        var Takes_Place: List<DateRecord>,

        var Sch_country: String

) : Serializable