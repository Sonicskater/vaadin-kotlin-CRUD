package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.projects.Project
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class Manager: User(), Serializable {
        @Display(DisplayTypeClasif.LIST, type = Project::class)
        @OneToMany(targetEntity = Project::class, mappedBy = "manager")
        var manages: MutableList<Project> = mutableListOf()

        override fun delete() {
                RepoHelper.managerRepository.deleteById(this.userKey)
        }

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.managerRepository as CrudRepository<T, ID>
        }


}
