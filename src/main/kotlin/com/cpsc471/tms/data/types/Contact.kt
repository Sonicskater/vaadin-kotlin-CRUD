package com.cpsc471.tms.data.types


import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "contact")
class Contact(
        @ManyToOne(fetch = FetchType.LAZY, targetEntity = Institute::class)
        /*
        @JoinColumns(
                JoinColumn(name = "Inst_country"),
                JoinColumn(name = "Inst_postal_code"),
                JoinColumn(name = "Inst_street_address"),
                JoinColumn(name = "Inst_city"),
                JoinColumn(name = "Inst_Province")
        */
        @Id var institute: Institute,
        @Id var Email: String,

        @OneToMany(targetEntity = ContactContactInfo::class, mappedBy = "contact")
        var manages: List<ContactContactInfo>
        ) : DBAbstract(), Serializable{
    override fun IDforDb() : List<Any> {
        return listOf(Email,institute)
    }

}