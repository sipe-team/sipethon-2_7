package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class PlanDetail(
    val day: Int,
    val schedule: List<Schedule>
)
