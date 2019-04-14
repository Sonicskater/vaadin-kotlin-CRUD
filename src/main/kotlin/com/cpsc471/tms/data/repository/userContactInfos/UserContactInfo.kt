package com.cpsc471.tms.data.repository.userContactInfos

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBAbstract
import com.cpsc471.tms.data.repository.DBKey
import com.vaadin.flow.data.binder.ValidationResult
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class UserContactInfo: DBAbstract(){


        @Display
        var userDescription: String? = null

        @Display(DisplayTypeClasif.COMPOSITE)
        @EmbeddedId
        var userContactInfoKey: UserContactInfoKey = UserContactInfoKey()

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.userContactInfoRepository as CrudRepository<T, ID>
        }

        override fun keyType(): Class<out DBKey> {
                return UserContactInfoKey::class.java
        }

        override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
                return Validator { t, valueContext ->
                        val t2 = t as UserContactInfo
                        if (RepoHelper.userContactInfoRepository.existsById(t2.userContactInfoKey)){
                                ValidationResult.error("")
                        }else{
                                ValidationResult.ok()
                        }
                }
        }

        override fun delete() {
                RepoHelper.userContactInfoRepository.deleteById(this.userContactInfoKey)
        }

        override fun iDforDb(): List<Any?> {
                return listOf(userContactInfoKey)
        }

}
