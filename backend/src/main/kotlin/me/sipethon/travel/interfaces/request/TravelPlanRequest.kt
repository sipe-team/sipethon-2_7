package me.sipethon.travel.interfaces.request

import me.sipethon.travel.domain.GroupType
import me.sipethon.travel.domain.Keyword

data class TravelPlanRequest(
    val location: String,
    val duration: Int,
    val people: Int,
    val budget: Int?,
    val groupType: GroupType?,
    val keywords: List<Keyword>
) {
    fun toTravelPlan(): String {
        val travelPlan = StringBuilder()
        location.let { travelPlan.append("$it 에 ") }
        people.let { travelPlan.append("$it 명이서 ") }
        duration.let { travelPlan.append("$it 동안 ") }
        groupType?.let { travelPlan.append("$it 여행을 ") }
        budget?.let { travelPlan.append("$it 금액으로 ") }

        keywords.let {
            if (it.isNotEmpty()) {
                travelPlan.append(it.joinToString(", ") + "들을 포함해서")
            }
        }
        travelPlan.append("여행 계획을 만들어줘.")
        travelPlan.append("각 스팟별로 차 또는 도보로 이동거리를 알려주고 스팟의 대표 사진도 2장의 링크도 추가해줘.")
        return travelPlan.toString()
    }
}
