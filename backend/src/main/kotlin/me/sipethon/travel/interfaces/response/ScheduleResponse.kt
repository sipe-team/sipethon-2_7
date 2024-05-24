package me.sipethon.travel.interfaces.response

data class ScheduleResponse(
    val title: String,
    val activities: List<ActivityResponse>
)
