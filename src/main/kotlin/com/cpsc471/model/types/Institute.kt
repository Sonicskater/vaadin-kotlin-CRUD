package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class Institute(

        @Id var Name:String = "",
    open var Country:String = "",
    @Id var Postal_code:String = "",
    open var Street_address:String = "",
    open var City:String = "",
    open var Province:String = "",
    open var Website:String = "",


    @OneToMany(mappedBy = "institute",targetEntity = Contact::class)
    open var contacts: List<Contact> = listOf()

): DBAbstract(), Serializable{
    override fun getID(): List<Any> {
        return listOf(Country, Postal_code, Street_address, City, Province)
    }
}