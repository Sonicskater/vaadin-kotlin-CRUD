package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.keys.DBKey
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
    override fun getKeyType(): Class<out DBKey> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iDforDb(): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}