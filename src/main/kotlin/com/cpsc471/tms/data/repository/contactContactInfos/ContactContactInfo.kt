package com.cpsc471.tms.data.repository.contactContactInfos

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name= "contact_contact_info")
class ContactContactInfo: DBAbstract(),Serializable{

    @EmbeddedId
    var contactContactInfoKey : ContactContactInfoKey = ContactContactInfoKey()

    @Display
    var description: String? = null


    override fun delete() {
        RepoHelper.contactContactInfoRepository.deleteById(contactContactInfoKey)
    }

    override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
        return Validator { t, valueContext ->
            if(RepoHelper.contactContactInfoRepository.existsById((t as ContactContactInfo).contactContactInfoKey)){
                ValidationResult.error("")
            }else{
                ValidationResult.ok()
            }
        }
    }

    override fun keyType(): Class<out DBKey> {
        return ContactContactInfoKey::class.java
    }

    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.contactContactInfoRepository as CrudRepository<T, ID>
    }

    override fun iDforDb(): List<Any> {
        return listOf(contactContactInfoKey)
    }

}