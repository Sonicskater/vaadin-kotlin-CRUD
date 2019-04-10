package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.keys.DBKey
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.jpa.repository.Temporal
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.TemporalType

@Entity
class SchoolGrantApplication(
        @Id
    @Display
    var name: String = "",
        @Display
    var status: String = "",
        @Display
    var amount: Int = 0,
        var website: String = "",

        @Temporal(TemporalType.DATE)
        var submissionDate: LocalDate = LocalDate.of(0,1,1),

        @Id
    @ManyToOne(targetEntity = FundingSource::class)
    var source: FundingSource = FundingSource(),

        @Id
    @ManyToOne(targetEntity = School::class)
    var applicant: School = School()

) : DBAbstract(), Serializable{
    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> getValidator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKeyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}