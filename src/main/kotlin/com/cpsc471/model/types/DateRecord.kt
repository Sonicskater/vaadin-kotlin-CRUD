package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "date_records")
data class DateRecord(
        @ManyToMany(targetEntity = Project::class)
        @JoinTable(name = "takes_place",
                inverseJoinColumns = [JoinColumn(name = "Date_key")],
                joinColumns =
                [
                    JoinColumn(name = "Month"),
                    JoinColumn(name = "Number"),
                    JoinColumn(name = "Year")
                ]
        )
        var Projects: List<Project>,
        @Id var Month: Int,
        @Id var Number: Int,
        @Id var Year: Int
):Serializable