package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="projects")
class Project(
        @Id
        var Date_key: Int,

        @ManyToMany(targetEntity = DateRecord::class)
        @JoinTable(name="takes_place",
                joinColumns = [
                    JoinColumn(name="Date_key"),
                    JoinColumn(name="Sch_country"),
                    JoinColumn(name="Sch_postal_code"),
                    JoinColumn(name="Sch_street_address"),
                    JoinColumn(name="Sch_city"),
                    JoinColumn(name="Sch_province")],
                inverseJoinColumns =
                    [
                        JoinColumn(name= "Month"),
                        JoinColumn(name = "Number"),
                        JoinColumn(name = "Year")
                    ]
                )
        var takes_place: List<DateRecord>,

        @Id
        @ManyToOne(fetch = FetchType.LAZY,targetEntity = School::class)
        @JoinColumns(
            JoinColumn(name="Sch_country", referencedColumnName="Country"),
            JoinColumn(name="Sch_postal_code", referencedColumnName="Postal_code"),
                JoinColumn(name="Sch_street_address", referencedColumnName="Street_address"),
                JoinColumn(name="Sch_city", referencedColumnName="City"),
                JoinColumn(name="Sch_province", referencedColumnName="Province")

        )
        var school : School,

        @ManyToMany(fetch = FetchType.LAZY, targetEntity = User::class)
        @JoinTable(name = "part_of",
                joinColumns = [
                    JoinColumn(name="Date_key"),
                    JoinColumn(name="Sch_country"),
                    JoinColumn(name="Sch_postal_code"),
                    JoinColumn(name="Sch_street_address"),
                    JoinColumn(name="Sch_city"),
                    JoinColumn(name="Sch_province")
                ],
                inverseJoinColumns = [
                    JoinColumn(name = "Art_email")
                ]
        )
        var members: List<User>,

        @ManyToOne(targetEntity = User::class)
        @JoinColumn(name = "Mgr_email")
        var manager: User,

        var theme: String

) : Serializable, DBAbstract() {
    override fun getID(): List<Any> {
        return listOf(Date_key,school)
    }
}