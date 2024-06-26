package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.domain.TravelPlanKeyword
import java.time.format.DateTimeFormatter

data class SearchHistoryResponse(
    val searchHistory: List<SearchHistoryDetailResponse>
) {
    constructor(travelPlanMap: Map<TravelPlan, List<TravelPlanKeyword>>) : this(
        travelPlanMap.map { (travelPlan, keywords) ->
            SearchHistoryDetailResponse(
                id = travelPlan.id,
                thumbnail = travelPlan.thumbnail,
                location = travelPlan.location,
                duration = "${travelPlan.duration}박 ${travelPlan.duration + 1}일",
                people = "${travelPlan.people}명",
                budget = travelPlan.budget?.let { "${it}만원" },
                groupType = travelPlan.groupType,
                keywords = keywords.map { it.keyword },
                createdAt = DateTimeFormatter.ofPattern("M월 d일, a h시").format(travelPlan.createdAt),
                isBookmarked = travelPlan.isBookmarked
            )
        }
    )
}
