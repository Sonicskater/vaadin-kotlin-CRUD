package com.cpsc471.tms.data.repository.projects

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.invoices.Invoice
import com.cpsc471.tms.data.repository.users.Artist
import com.cpsc471.tms.data.repository.users.Manager
import com.cpsc471.tms.data.repository.vehicles.Vehicle
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

@Entity
class Project: DBAbstract() {
    @Display
    var title: String? = null

    @Display(clasif = DisplayTypeClasif.COMPOSITE)
    @EmbeddedId
    var projectKey: ProjectKey = ProjectKey()

    @Display(clasif = DisplayTypeClasif.LIST, type = Artist::class, category = DisplayCategory.VERBOSE)
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Artist::class,cascade= [CascadeType.ALL])
    var members: MutableList<Artist> = mutableListOf()

    @ManyToOne(targetEntity = Manager::class)
    var manager: Manager? = null

    var theme: String? = null

    @Display(DisplayTypeClasif.OBJECT, type = Vehicle::class, category = DisplayCategory.VERBOSE)
    @ManyToOne(cascade= [CascadeType.ALL],targetEntity = Vehicle::class)
    var vehicle: Vehicle? = null

    @Display(DisplayTypeClasif.OBJECT, type = Invoice::class, category = DisplayCategory.VERBOSE)
    @OneToOne(cascade= [CascadeType.ALL],targetEntity = Invoice::class)
    var invoice: Invoice? = null

    @Transient
    override fun delete() {
        try {
            RepoHelper.projectRepository.existsById(projectKey)
        }catch (e : Exception){

        }
    }


    @Transient
    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { project, _ ->
            if (RepoHelper.projectRepository.existsById((project as Project).projectKey) || (project as Project) == Project()){
                ValidationResult.error("Already exists")
            }else{
                if (project.projectKey.start?.isBefore(project.projectKey.end) == true) ValidationResult.ok() else ValidationResult.error("Invalid dates")
            }
        }
    }

    @Transient
    override fun keyType(): Class<out DBKey> {
        return ProjectKey::class.java
    }

    @Transient
    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.projectRepository as CrudRepository<T, ID>
    }

    @Transient
    override fun iDforDb(): List<Any> {
        return listOf(projectKey)
    }

}