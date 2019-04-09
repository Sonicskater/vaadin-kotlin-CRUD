package com.cpsc471.tms.data.types
import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.UserKey
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class User(
        @Display
        open var firstName: String = "",

        @Display
        open var lastName: String = "",

        @EmbeddedId
        var userKey: UserKey = UserKey(),

        open var country: String = "",
        open var province: String = "",

        open var city: String = "",
        open var streetAddress: String = "",
        open var postalCode: String = "",
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

        open var password: String = "",

        @OneToMany(targetEntity = UserContactInfo::class, mappedBy = "user")
        open var contactInfo: MutableList<UserContactInfo> = mutableListOf()






) : DBAbstract(), Serializable {
    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKeyType(): Class<out DBKey> {
        return userKey::class.java
    }

    override fun iDforDb(): List<Any> {
        return(listOf(userKey))
    }
}