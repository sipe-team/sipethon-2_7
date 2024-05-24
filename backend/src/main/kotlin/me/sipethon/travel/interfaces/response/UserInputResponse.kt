package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.GroupType
import me.sipethon.travel.domain.Keyword

data class UserInputResponse(
    val location: String,
    val duration: String,
    val people: String,
    val budget: String?,
    val groupType: GroupType?,
    val keywords: List<Keyword>
)
