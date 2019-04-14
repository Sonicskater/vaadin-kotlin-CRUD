package com.cpsc471.tms.ui

import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("")
class LandingView : VerticalLayout() {

    init {
        defaultHorizontalComponentAlignment = FlexComponent.Alignment.CENTER
        val horizontalLayout = HorizontalLayout()

        //Empty lines to space page
        for ( i in 1..10){
            val label = Label("")
            label.height="1em"
            add(label)
        }
        val label = Label("Theatre \nManagement \n System")
        add(label)
        add(horizontalLayout)
        horizontalLayout.defaultVerticalComponentAlignment = FlexComponent.Alignment.CENTER
        val login = Button("Login") {

        }
        horizontalLayout.add(login)
        val signup = Button("Sign Up") {

        }
        horizontalLayout.add(signup)

        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        signup.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
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