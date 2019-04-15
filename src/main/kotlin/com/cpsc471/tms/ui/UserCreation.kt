package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.users.User
import com.cpsc471.tms.ui.components.DBObjectForm
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Route("sign-up")
class UserCreation : VerticalLayout() {

    private val dbObjectForm = DBObjectForm(User::class.java, creatable = true, editable = true)

    private val email = EmailField("Email")

    private val password = PasswordField("Password")

    private val firstName = TextField("First Name")

    private val lastName = TextField("Last Name")

    init {
        add(email)
        add(password)
        add(firstName)
        add(lastName)
        password.isRequired = true
        add(Button("Register"){
            val user = User()
            user.userKey.email = email.value
            user.password = BCryptPasswordEncoder(11).encode(password.value)
            user.firstName = firstName.value
            user.lastName = lastName.value

            RepoHelper.userRepository.save(user)
        })

    }
    override fun onAttach(attachEvent: AttachEvent) {
        super.onAttach(attachEvent)
        /**
         * Using the @Theme Annotation to set the Dark Theme causes issues with shadows which will appear in
         * the wrong color making them seemingly invisible. Instead do it the following way as long as the issue is not
         * solved (https://github.com/vaadin/flow/issues/4765)
         */
        ui.get().page.executeJavaScript("document.documentElement.setAttribute(\"theme\",\"dark\")")
    }
}