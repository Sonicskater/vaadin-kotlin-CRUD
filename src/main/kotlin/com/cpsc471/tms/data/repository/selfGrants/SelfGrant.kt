package com.cpsc471.tms.data.repository.selfGrants

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.ui.crudpages.SelfGrantsView
import com.vaadin.flow.component.UI
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.sql.Date
import javax.persistence.EmbeddedId
import javax.persistence.Entity
@Entity
class SelfGrant: DBAbstract(), Serializable {
    override fun view(ui: UI) {
        ui.navigate(SelfGrantsView::class.java)
    }


    @EmbeddedId
    var selfGrantKey : SelfGrantKey = SelfGrantKey()

    var status: String? = null
    var amonut: Int = 0
    var website: String? = null
    var submissionDate: Date? = null


    override fun delete() {
        RepoHelper.selfGrantRepository.deleteById(this.selfGrantKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { t, valueContext ->
            val t2 = t as SelfGrant
            if(RepoHelper.selfGrantRepository.existsById(t2.selfGrantKey)){
                ValidationResult.error("")
            }else{
                ValidationResult.ok()
            }
        }
    }

    override fun keyType(): Class<out DBKey> {
        return SelfGrantKey::class.java
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.selfGrantRepository as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(selfGrantKey)
    }

}