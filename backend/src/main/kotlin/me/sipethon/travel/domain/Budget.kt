package me.sipethon.travel.domain

import kotlinx.serialization.Serializable

@Serializable
data class Budget(
    val accommodationMain: String,
    val accommodationSub: String,
    val transportation: String,
)
