package com.example.movieappmad24.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long = 0,
    var id: String,
    var title: String,
    var year: String,
    var genre: String,
    var director: String,
    var actors: String,
    var plot: String,
    @Ignore
    var images: List<String> = listOf(),
    var trailer: String,
    var rating: String,
    var isFavorite: Boolean = false
) {
    constructor(): this(0,"","","","","","","", listOf(),"","",false)
}

