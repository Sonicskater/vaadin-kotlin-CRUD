package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.modals.ObjectSelectionModal
import com.cpsc471.tms.ui.components.modals.TableSelectionModal
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import javafx.scene.control.TableSelectionModel
import javax.security.sasl.AuthorizeCallback

class ObjectFieldUI<T : DBAbstract>(
        clazz: Class<T>,
        dbObject: T,
        readOnly: Boolean = true,
        callback: (T)-> Unit
) : VerticalLayout(){

    val dbObjectForm = DBObjectForm(clazz, verbose = false)
    val verticalLayout =VerticalLayout()
    val horizontalLayout = HorizontalLayout()

    init {
        add(verticalLayout)

        verticalLayout.add(dbObjectForm)
        verticalLayout.add(horizontalLayout)

        if(!readOnly){
            horizontalLayout.add(Button("Edit",VaadinIcon.PENCIL.create()){
                val modal = ObjectSelectionModal(clazz,dbObject){



                    callback(it)
                }

                modal.open()

            })
        }
        dbObjectForm.setObject(dbObject).render()
    }





}