package com.cpsc471.model.types

import org.hibernate.annotations.Check
import java.io.Serializable
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name="project")
//@Check(constraints = "")
class Project(
        /**
        @ManyToMany(targetEntity = DateRecord::class)
        @JoinTable(name="takes_place",
                joinColumns = [
                    JoinColumn(name="ProjectID"),
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
        */

        /*
        @Id
        @ManyToOne
        @JoinColumns(
                JoinColumn(name="Start_number", referencedColumnName="Number"),
                JoinColumn(name="Start_month", referencedColumnName="Month"),
                JoinColumn(name="Start_year", referencedColumnName="Year"))
        var start: DateRecord,
        @Id
        @ManyToOne
        @JoinColumns(
                JoinColumn(name="End_number", referencedColumnName="Number"),
                JoinColumn(name="End_month", referencedColumnName="Month"),
                JoinColumn(name="End_year", referencedColumnName="Year"))
        var end: DateRecord,
        */

        @Id
        var start: Date,

        @Id
        var end: Date,

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
                    /*
                    JoinColumn(name="Start_number"),
                    JoinColumn(name="Start_month"),
                    JoinColumn(name="Start_year"),
                    JoinColumn(name="End_number"),
                    JoinColumn(name="End_month"),
                    JoinColumn(name="End_year"),
                    */
                    JoinColumn(name = "start"),
                    JoinColumn(name = "end"),
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
        var title: String,
        var theme: String,

        @ManyToOne(targetEntity = Vehicle::class)
        var vehicle: Vehicle?,

        @OneToOne
        var invoice: Invoice

) : Serializable, DBAbstract() {
    override fun getID(): List<Any> {
        return listOf(start,end,school)
    }
}