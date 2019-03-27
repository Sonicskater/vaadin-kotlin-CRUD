package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
abstract class Institute(
    @Id var Country:String = "",
    @Id var Postal_code:String = "",
    @Id var Street_address:String = "",
    @Id var City:String = "",
    @Id var Province:String = "",
    open var Website:String = "",
    open var Name:String = "",

    @OneToMany(mappedBy = "institute",targetEntity = Contact::class)
    open var contacts: List<Contact> = listOf()

): DBAbstract(), Serializable{
    override fun getID(): List<Any> {
        return listOf(Country, Postal_code, Street_address, City, Province)
    }
}