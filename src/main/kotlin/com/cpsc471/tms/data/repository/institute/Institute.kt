package com.cpsc471.tms.data.repository.institute

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.contacts.Contact
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
class Institute: DBAbstract(), Serializable{

    @EmbeddedId
    @Display(clasif = DisplayTypeClasif.COMPOSITE)
    open var instituteKey: InstituteKey = InstituteKey()

    @Display(category = DisplayCategory.VERBOSE)
    open var country:String? = null

    @Display(category = DisplayCategory.VERBOSE)
    open var streetAddress:String? = null

    @Display
    open var city:String? = null

    @Display(category = DisplayCategory.VERBOSE)
    open var province:String? = null

    @Display(category = DisplayCategory.VERBOSE)
    open var website:String? = null

    @Display(clasif = DisplayTypeClasif.LIST, type = Contact::class)
    @OneToMany(mappedBy = "contactKey.institute",targetEntity = Contact::class)
    open var contacts: MutableList<Contact> = mutableListOf()
    override fun delete() {
        RepoHelper.instituteRepository.deleteById(instituteKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { inst, _ ->

            if (RepoHelper.schoolRepository.existsById((inst as Institute).instituteKey) || RepoHelper.instituteRepository.existsById((inst as Institute).instituteKey)) {
                ValidationResult.error("School already exists!")
            }else{
                ValidationResult.ok()
            }

        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.instituteRepository as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(instituteKey)
    }

    override fun keyType(): Class<out DBKey> {
        return InstituteKey::class.java
    }

}