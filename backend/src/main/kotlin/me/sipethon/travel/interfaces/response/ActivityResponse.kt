package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.ActivityType

data class ActivityResponse(
    val title: String,
    val category: String?,
    val type: ActivityType,
    val imgUrl: String?,
)
