package com.cpsc471.model.types
import java.io.Serializable
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
open class User(
        open var First_name: String = "",
        open var Last_name: String = "",
        @Id var Email: String = "",
        open var Country: String = "",
        open var Province: String = "",
        open var City: String = "",
        open var Street_address: String = "",
        open var Postal_code: String = "",
        open var User_type: String = "",
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







        @OneToMany(targetEntity = UserContactInfo::class, mappedBy = "user")
        open var contact_info: List<UserContactInfo> = mutableListOf()






) : DBAbstract(), Serializable {
        override fun getID(): List<Any> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
}