package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.ProjectKey
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="project")
@Configurable(dependencyCheck = true)
class Project(

        @Display
        var title: String = "",

        @Display(clasif = DisplayTypeClasif.COMPOSITE)
        @EmbeddedId
        var projectKey: ProjectKey = ProjectKey(),

        @Display(clasif = DisplayTypeClasif.LIST, type = Artist::class, category = DisplayCategory.VERBOSE)
        @ManyToMany(fetch = FetchType.LAZY, targetEntity = Artist::class)
        var members: List<Artist> = listOf(),

        @ManyToOne(targetEntity = Manager::class)
        var manager: Manager? = null,

        var theme: String = "",

        @ManyToOne(targetEntity = Vehicle::class)
        var vehicle: Vehicle? = null,

        @OneToOne
        var invoice: Invoice? = null

) : Serializable, DBAbstract() {
    override fun getKeyType(): Class<out DBKey> {
        return ProjectKey::class.java
    }

    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.projectRepository as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(projectKey)
    }

}