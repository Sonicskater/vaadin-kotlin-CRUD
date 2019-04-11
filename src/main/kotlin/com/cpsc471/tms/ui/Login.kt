package com.cpsc471.tms.ui

import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.Tag
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route


@Tag("sa-login")
@Route(value = Login.ROUTE)
@PageTitle("Login")
class Login : VerticalLayout() {

    private val login = LoginOverlay() //

    init {
        login.action = "login" //
        login.isOpened = true //
        login.setTitle("Spring Secured Vaadin")
        login.description = "Login Overlay Example"
        element.appendChild(login.element) //
        login.isForgotPasswordButtonVisible = false
        val i18n = LoginI18n.createDefault()
        //i18n.additionalInformation = "To close the login form submit non-empty username and password"
        login.setI18n(i18n)
    }

    companion object {
        const val ROUTE = "login"
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