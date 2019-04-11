package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.projects.Project
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class Manager(
        @OneToMany(targetEntity = Project::class, mappedBy = "manager")
        var manages: MutableList<Project> = mutableListOf()
): User(), Serializable {
        override fun delete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun keyType(): Class<out DBKey> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

}
