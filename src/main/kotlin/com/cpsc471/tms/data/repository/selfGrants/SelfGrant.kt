package com.cpsc471.tms.data.repository.selfGrants

import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.FundingSource
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
@Entity
class SelfGrant(
        @Id
        var name: String,
        var status: String,
        var amonut: Int,
        var website: String,
        var submissionDate: Date,

        @Id
        @ManyToOne(targetEntity = FundingSource::class)
        var source: FundingSource
) : DBAbstract(), Serializable {
    override fun delete() {

    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}