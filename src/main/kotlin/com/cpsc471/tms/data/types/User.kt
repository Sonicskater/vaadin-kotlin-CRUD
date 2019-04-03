package com.cpsc471.tms.data.types
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class User(
        open var firstName: String = "",
        open var lastName: String = "",
        @Id var email: String = "",
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
    override fun IDforDb(): List<Any> {
        return(listOf(email))
    }
}