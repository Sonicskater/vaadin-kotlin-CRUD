package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.InstituteKey
import com.cpsc471.tms.ui.components.*
import org.springframework.data.repository.CrudRepository
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
        @Display(category = DisplayCategory.VERBOSE)
    open var country:String = "",

        @DisplayDetail
        @Editable
        @Display(category = DisplayCategory.VERBOSE)
    open var streetAddress:String = "",

        @DisplayOld
        @Editable
        @Display
    open var city:String = "",

        @DisplayDetail
        @Editable
        @Display(category = DisplayCategory.VERBOSE)
    open var province:String = "",

        @DisplayDetail
        @Editable
        @Display(category = DisplayCategory.VERBOSE)
    open var website:String = "",

        @DisplayList(Contact::class)
        @EditableList(Contact::class)
        @Display(clasif = DisplayTypeClasif.LIST, type = Contact::class)
    @OneToMany(mappedBy = "contactKey.institute",targetEntity = Contact::class)
    open var contacts: List<Contact> = listOf()

): DBAbstract(), Serializable{
    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        return listOf(instituteKey)
    }

    override fun toString(): String {
        return instituteKey.name
    }

    override fun getKeyType(): Class<out DBKey> {
        return InstituteKey::class.java
    }

}