package com.cpsc471.tms.data.repository.schoolGrantApplications

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.ui.crudpages.SchoolGrantsView
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class SchoolGrantApplication : DBAbstract(), Serializable{
    override fun view(ui: UI) {
        ui.navigate(SchoolGrantsView::class.java)
    }

    @Display(DisplayTypeClasif.COMPOSITE)
    @EmbeddedId
    var schoolGrantApplicationKey = SchoolGrantApplicationKey()

    @Display
    var status: String? = null
    @Display
    var amount: Int = 0

    @Display(category = DisplayCategory.VERBOSE)
    var website: String? = null


    var submissionDate: LocalDate = LocalDate.of(0,1,1)

    override fun delete() {
        RepoHelper.schoolGrantApplicationsRepository.deleteById(this.schoolGrantApplicationKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { t, _ ->
            val t2 = t as SchoolGrantApplication
            if(RepoHelper.schoolGrantApplicationsRepository.existsById(t2.schoolGrantApplicationKey)){
                ValidationResult.error("")
            }else{
                ValidationResult.ok()
            }
        }
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.schoolGrantApplicationsRepository as CrudRepository<T, ID>
    }

    override fun keyType(): Class<out DBKey> {
        return SchoolGrantApplicationKey::class.java
    }

    override fun iDforDb(): List<Any> {
        return listOf(schoolGrantApplicationKey)
    }

}