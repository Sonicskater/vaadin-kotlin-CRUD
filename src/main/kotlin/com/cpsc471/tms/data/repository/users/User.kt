package com.cpsc471.tms.data.repository.users
import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.userContactInfos.UserContactInfo
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class User: DBAbstract(), Serializable {

    @Display
    open var firstName: String? =null

    @Display
    open var lastName: String? =null

    @Display(DisplayTypeClasif.COMPOSITE)
    @EmbeddedId
    var userKey: UserKey = UserKey()

    @Display(category = DisplayCategory.VERBOSE)
    open var country: String? = null
    @Display(category = DisplayCategory.VERBOSE)
    open var province: String? = null

    @Display
    open var city: String? = null
    @Display(category = DisplayCategory.VERBOSE)
    open var streetAddress: String? = null
    @Display(category = DisplayCategory.VERBOSE)
    open var postalCode: String? = null
    /**
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
     */

    open var password: String? = null


    @Display(DisplayTypeClasif.LIST, type = UserContactInfo::class)
    @OneToMany(targetEntity = UserContactInfo::class, mappedBy = "userContactInfoKey.user")
    open var contactInfo: MutableList<UserContactInfo> = mutableListOf()


    override fun delete() {
        RepoHelper.userRepository.deleteById(this.userKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { user, _ ->
            val user1 = user as User
            when{
                RepoHelper.userRepository.existsById(user1.userKey) -> ValidationResult.error("Exists as unassigned")
                RepoHelper.artistRepository.existsById(user1.userKey) -> ValidationResult.error("Exists as artist")
                RepoHelper.managerRepository.existsById(user1.userKey) -> ValidationResult.error("Exists as manager")
                else -> ValidationResult.ok()
            }
        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.userRepository as CrudRepository<T, ID>
    }

    override fun keyType(): Class<out DBKey> {
        return userKey::class.java
    }

    override fun iDforDb(): List<Any> {
        return(listOf(userKey))
    }
}