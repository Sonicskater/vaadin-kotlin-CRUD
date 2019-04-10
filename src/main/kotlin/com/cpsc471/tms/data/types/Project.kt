package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.keys.DBKey
import com.cpsc471.tms.data.keys.ProjectKey
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
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
        @ManyToMany(fetch = FetchType.LAZY, targetEntity = Artist::class,cascade= [CascadeType.ALL])
        var members: List<Artist> = listOf(),

        @ManyToOne(targetEntity = Manager::class)
        var manager: Manager? = null,

        var theme: String = "",

        @Display(DisplayTypeClasif.OBJECT, type = Vehicle::class, category = DisplayCategory.VERBOSE)
        @ManyToOne(cascade= [CascadeType.ALL],targetEntity = Vehicle::class)
        var vehicle: Vehicle = Vehicle(),

        @OneToOne(cascade= [CascadeType.ALL],targetEntity = Invoice::class)
        var invoice: Invoice

) : Serializable, DBAbstract() {
    init {
        invoice.project=this
    }
    override fun delete() {
        try {
            RepoHelper.projectRepository.existsById(projectKey)
        }catch (e : Exception){

        }
    }


    override fun <T> getValidator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { project, _ ->
            if (RepoHelper.projectRepository.existsById((project as Project).projectKey) || (project as Project) == Project()){
                ValidationResult.error("Already exists")
            }else{
                if (project.projectKey.start.isBefore(project.projectKey.end)) ValidationResult.ok() else ValidationResult.error("Invalid dates")
            }
        }
    }

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