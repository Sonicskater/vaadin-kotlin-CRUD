package com.cpsc471.view.components

import com.cpsc471.model.repos.ArtistRepository
import com.cpsc471.model.types.Artist
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class ArtistListViewer(val repo : ArtistRepository) : VerticalLayout() {


    val grid : Grid<Artist>
    init {
        grid = Grid()

        grid.addColumn(Artist::email).setHeader("Email")
        grid.addColumn{
            it.firstName+" "+it.lastName
        }.setHeader("Name")
        grid.addColumn(Artist::country).setHeader("Country")
        grid.addColumn(Artist::city).setHeader("City")
        add(grid)
        listArtists(repo.findAll() as List<Artist>)
    }

    fun listArtists(list: List<Artist>) {
        grid.setItems(list)
    }

}