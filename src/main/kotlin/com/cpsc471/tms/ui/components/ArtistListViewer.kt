package com.cpsc471.tms.ui.components

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.types.Artist
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class ArtistListViewer(private val repo : ArtistRepository,
                       selectionMode: Grid.SelectionMode,
                       private val artistViewer: ArtistViewer? = null) : VerticalLayout() {


    private val grid : Grid<Artist> = Grid()

    init {
        grid.setSelectionMode(selectionMode)
        grid.addColumn(Artist::email).setHeader("Email")
        grid.addColumn{
            it.firstName+" "+it.lastName
        }.setHeader("Name")
        grid.addColumn(Artist::country).setHeader("Country")
        grid.addColumn(Artist::city).setHeader("City")
        add(grid)
        listArtists(repo.findAll() as List<Artist>)

        grid.selectionModel.addSelectionListener {
            updateViewer()
        }

    }

    fun listArtists(list: List<Artist>) {
        grid.setItems(list)
    }

    fun selectedSingle() : Artist{
        return selectedMulti().first()
    }
    fun selectedMulti() : MutableSet<Artist>{
        return grid.selectionModel.selectedItems
    }
    private fun updateViewer(){
        artistViewer?.display(selectedSingle())
    }

}