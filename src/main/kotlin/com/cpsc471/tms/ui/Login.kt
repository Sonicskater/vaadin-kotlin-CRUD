package com.cpsc471.tms.ui

import com.github.mvysny.karibudsl.v8.navigateToView
import com.vaadin.flow.component.Tag
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.login.LoginI18n




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
}