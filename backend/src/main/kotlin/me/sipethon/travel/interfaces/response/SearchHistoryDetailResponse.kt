package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.GroupType
import me.sipethon.travel.domain.Keyword

data class SearchHistoryDetailResponse(
    val id: Long,
    val thumbnail: String,
    val location: String,
    val duration: String,
    val people: String,
    val budget: String?,
    val groupType: GroupType?,
    val keywords: List<Keyword>,
    val createdAt: String,
    val isBookmarked: Boolean
)
