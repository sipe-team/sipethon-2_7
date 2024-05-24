package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.GroupType
import me.sipethon.travel.domain.Keyword

data class UserInputResponse(
    val location: String,
    val duration: Int,
    val people: Int,
    val budget: Int?,
    val groupType: GroupType?,
    val keywords: List<Keyword>
)
