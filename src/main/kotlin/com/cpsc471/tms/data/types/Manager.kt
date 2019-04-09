package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.keys.DBKey
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class Manager(
        @OneToMany(targetEntity = Project::class, mappedBy = "manager")
        var manages: List<Project> = mutableListOf()
): User(), Serializable {
        override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getKeyType(): Class<out DBKey> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

}
