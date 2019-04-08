package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.ui.components.*
import java.io.Serializable
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class Institute(


        @EmbeddedId
        @Display(clasif = DisplayTypeClasif.COMPOSITE)
        @DisplayComposite
        @EditableComposite
        open var instituteKey: InstituteKey = InstituteKey(),

        @DisplayDetail
        @Editable
        @Display
    open var country:String = "",

        @DisplayDetail
        @Editable
        @Display
    open var streetAddress:String = "",

        @DisplayOld
        @Editable
    open var city:String = "",

        @DisplayDetail
        @Editable
    open var province:String = "",

        @DisplayDetail
        @Editable
    open var website:String = "",

        @DisplayList(Contact::class)
        @EditableList(Contact::class)
    @OneToMany(mappedBy = "contactKey.institute",targetEntity = Contact::class)
    open var contacts: List<Contact> = listOf()

): DBAbstract(), Serializable{
    override fun IDforDb(): List<Any> {
        return listOf(instituteKey)
    }

    override fun toString(): String {
        return instituteKey.name
    }
}