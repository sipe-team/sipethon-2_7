package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val title: String,
    val category: String?,
    val type: ActivityType,
    var imgUrl: String?
)
