package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "school")
data class School (
    @Id var Country:String,
    @Id var Postal_code:String,
    @Id var Street_address:String,
    @Id var City:String,
    @Id var Province:String,
    var Website:String,
    var Type:Int,
    var Name:String,
    var Grade_min:Int,
    var Grade_max:Int,

    @OneToMany(targetEntity = Project::class, mappedBy = "school")
    var projects:List<Project>
) : Serializable
