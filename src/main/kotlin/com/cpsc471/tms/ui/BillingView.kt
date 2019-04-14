package com.cpsc471.tms.ui

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.invoiceItems.InvoiceItem
import com.cpsc471.tms.data.repository.invoices.Invoice
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.ui.components.DBObjectList
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route

@Route("billing", layout = BaseAppLayout::class)
class BillingView : VerticalLayout(){
    val projectList = DBObjectList(Project::class.java)
    val grid = Grid<InvoiceItem>(InvoiceItem::class.java, false)
    val horizontalLayout = HorizontalLayout()

    val total = Label("")
    init {

        projectList.addSelectionListener {
            update()
        }




        grid.addColumn("invoiceItemKey.itemNumber")
        grid.addColumn("description")
        grid.addColumn("amount")
        projectList.setItems(RepoHelper.projectRepository.findAll() as Collection<Project>)
        update()
    }
    fun update(){

        removeAll()
        add(projectList)
        add(horizontalLayout)
        horizontalLayout.removeAll()
        if (projectList.selected()?.invoice == null) {
            horizontalLayout.add(Button("Create missing invoice") {
                val invoice = Invoice()
                RepoHelper.invoiceRepository.save(invoice)
                projectList.selected()?.invoice = invoice
                projectList.selected()?.let {
                    RepoHelper.projectRepository.save(it)
                    projectList.setItems(RepoHelper.projectRepository.findAll() as Collection<Project>)
                    update()
                }
            })
        }else{
            add(grid)
            val horizontalLayout1 = HorizontalLayout()
            add(horizontalLayout1)
            horizontalLayout1.defaultVerticalComponentAlignment = FlexComponent.Alignment.CENTER
            horizontalLayout1.add("Total cost:")
            horizontalLayout1.add(total)
            val invoice = projectList.selected()?.invoice
            total.text = invoice?.sum()

            val horizontalLayout2 = HorizontalLayout()
            add(horizontalLayout2)
            val lineNum = NumberField("Line #")
            val desc = TextField("Description")
            val amount = NumberField("Amount")
            horizontalLayout2.add(lineNum, desc, amount , Button("Add item", VaadinIcon.DOLLAR.create()){
                val invoiceItem = InvoiceItem()
                invoiceItem.invoiceItemKey.itemNumber = lineNum.value.toInt()
                invoiceItem.description = desc.value
                invoiceItem.amount = amount.value.toInt()
                invoiceItem.invoiceItemKey.invoice = invoice
                RepoHelper.invoiceItemRepository.save(invoiceItem)
                invoice?.items?.add(invoiceItem)
                invoice?.let{RepoHelper.invoiceRepository.save(it)}
                projectList.setItems(RepoHelper.projectRepository.findAll() as Collection<Project>)
                update()
            })
            horizontalLayout2.add(Button("Delete item", VaadinIcon.TRASH.create()){
                val element = grid.selectedItems.first()
                invoice?.items?.remove(element)
                RepoHelper.invoiceItemRepository.delete(element)
                projectList.setItems(RepoHelper.projectRepository.findAll() as Collection<Project>)
                update()
            })
        }

        projectList.selected()?.invoice?.items?.let {  grid.setItems(it)}

    }
}