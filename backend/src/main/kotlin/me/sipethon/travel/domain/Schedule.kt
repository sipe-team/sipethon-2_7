package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val title: String,
    val activities: List<Activity>
)
