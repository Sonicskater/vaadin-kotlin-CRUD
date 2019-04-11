package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.InstituteKey
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
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
        open var instituteKey: InstituteKey = InstituteKey(),

        @Display(category = DisplayCategory.VERBOSE)
    open var country:String = "",

        @Display(category = DisplayCategory.VERBOSE)
    open var streetAddress:String = "",

        @Display
    open var city:String = "",

        @Display(category = DisplayCategory.VERBOSE)
    open var province:String = "",

        @Display(category = DisplayCategory.VERBOSE)
    open var website:String = "",

        @Display(clasif = DisplayTypeClasif.LIST, type = Contact::class)
    @OneToMany(mappedBy = "contactKey.institute",targetEntity = Contact::class)
    open var contacts: MutableList<Contact> = mutableListOf()

): DBAbstract(), Serializable{
    override fun delete() {
        RepoHelper.instituteRepository.deleteById(instituteKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { inst, context ->

            if (RepoHelper.schoolRepository.existsById((inst as Institute).instituteKey) || RepoHelper.instituteRepository.existsById((inst as Institute).instituteKey)) {
                ValidationResult.error("School already exists!")
            }else{
                ValidationResult.ok()
            }

        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        return listOf(instituteKey)
    }

    override fun toString(): String {
        return instituteKey.name
    }

    override fun keyType(): Class<out DBKey> {
        return InstituteKey::class.java
    }

}