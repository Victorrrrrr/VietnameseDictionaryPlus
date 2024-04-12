package com.gp.learn.data

data class ItemMatch(
    val id : Int,
    val wordString : String,
    var isChosen: Boolean,
    var readyDelete: Boolean
)
