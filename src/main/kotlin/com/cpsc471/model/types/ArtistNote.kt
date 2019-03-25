package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "artist_notes")
data class ArtistNote(
        @Id var Art_email:String,
        @Id var Art_note:String
) : Serializable