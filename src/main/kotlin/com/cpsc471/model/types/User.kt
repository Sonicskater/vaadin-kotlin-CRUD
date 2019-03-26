package com.cpsc471.model.types
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        var First_name: String,
        var Last_name: String,
        @Id var Email: String,
        var Type: String,
        var Country: String,
        var Province: String,
        var City: String,
        var Street_address: String,
        var Postal_code: String,
        var User_type: String,

        @ManyToMany(targetEntity = DateRecord::class)
        @JoinTable(name = "is_available",
                joinColumns = [JoinColumn(name = "Email")],
                inverseJoinColumns =
                [
                    JoinColumn(name = "Month"),
                    JoinColumn(name = "Year"),
                    JoinColumn(name = "Number")
                ])
        var available_days: List<DateRecord>,


        @ManyToMany(targetEntity = Project::class, mappedBy = "members")
        var projects: List<Project>,

        @OneToMany(targetEntity = Project::class, mappedBy = "manager")
        var manages: List<Project>

)