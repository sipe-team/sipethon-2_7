package me.sipethon.travel.domain

data class Activity(
    val title: String,
    val category: String,
    val type: ActivityType,
    val imgUrl: String?
)
