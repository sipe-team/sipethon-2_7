package me.sipethon.travel.domain

data class Plan(
    val title: String,
    val thumbnail: String,
    val budget: Budget,
    val travelPlan: List<PlanDetail>
)
