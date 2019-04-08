package com.cpsc471.tms.ui.components

import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import org.springframework.data.repository.CrudRepository
import java.lang.reflect.Field

class DataBaseListView<T : Any, V>(private val repo: CrudRepository<T, V>,
                                   selectionMode: Grid.SelectionMode,
                                   private val classT: Class<T>,
                                   private var selectionListener: (it : T) -> Unit = {}) : VerticalLayout(){

    private val grid : Grid<T> = Grid(classT,false)


    init {
        grid.setSelectionMode(selectionMode)

        addReflectedColumns(classT.superclass.declaredFields)
        addReflectedColumns(classT.declaredFields)

        add(grid)

        list(repo.findAll() as List<T>)

        grid.selectionModel.addSelectionListener {
            updateViewer()
        }

    }

    private fun addReflectedColumns(declaredFields: Array<Field>, prefix : String = "") {
        for (m in declaredFields) {
            /*println(m.name)
            /for ( i in m.declaredAnnotations){
                println(i.annotationClass)
            }
            */
            if (m.getAnnotation(DisplayOld::class.java) != null) {
                grid.addColumn(prefix + m.name)
                //println(m.name)
            } else if (m.getAnnotation(DisplayComposite::class.java) !=null){
                //println(m.name)
                addReflectedColumns(m.type.declaredFields,m.name+".")
            }
        }
    }

    private fun updateViewer() {
        this.selectedSingle()?.let { selectionListener(it) }
    }

    private fun selectedSingle(): T?{
        return selectedMulti().firstOrNull()
    }

    private fun selectedMulti(): MutableSet<T>{
        return grid.selectionModel.selectedItems
    }

    fun list(list : List<T>){
        grid.setItems(list)
    }









}