package me.sipethon.travel.interfaces.response

data class TravelPlanDetailResponse(
    val day: Int,
    val schedule: List<ScheduleResponse>
)
