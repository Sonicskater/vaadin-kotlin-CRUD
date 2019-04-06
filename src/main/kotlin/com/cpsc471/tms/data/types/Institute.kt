package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.ui.components.Display
import com.cpsc471.tms.ui.components.DisplayComposite
import java.io.Serializable
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class Institute(


        @EmbeddedId
        @DisplayComposite
        open var instituteKey: InstituteKey = InstituteKey(),

        //@Id var Name:String = "",
        @Display
    open var country:String = "",
    //@Id var Postal_code:String = "",
    open var streetAddress:String = "",
        @Display
    open var city:String = "",
    open var province:String = "",
    open var website:String = "",


    @OneToMany(mappedBy = "institute",targetEntity = Contact::class)
    open var contacts: List<Contact> = listOf()

): DBAbstract(), Serializable{
    override fun IDforDb(): List<Any> {
        return listOf(instituteKey.name, instituteKey.postalCode)
    }

    override fun toString(): String {
        return instituteKey.name
    }
}