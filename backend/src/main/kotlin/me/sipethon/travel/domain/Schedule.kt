package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val title: String,
    val activities: List<Activity>,

    // get all images in activities
    val titles: List<String> = activities.filter{ it.type == ActivityType.COMPLEX }.map { it.title }
)
