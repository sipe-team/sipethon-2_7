package me.spiethon.travel.domain

data class TravelPlanPreferences(
    val location: String?,
    val people: String?,
    val duration: String?,
    val budget: String?,
    val interests: Interests?
) {
    fun toTravelPlan(): String {
        val travelPlan = StringBuilder()
        location?.let { travelPlan.append("$it 에 ") }
        people?.let { travelPlan.append("$it 명이서 ") }
        duration?.let { travelPlan.append("$it 동안 ") }
        budget?.let { travelPlan.append("$it 금액으로 ") }

        interests?.toInterestList()?.let {
            if (it.isNotEmpty()) {
                travelPlan.append(it.joinToString(", ") + "들을 포함해서")
            }
        }
        travelPlan.append("여행 계획을 만들어줘.")
        travelPlan.append("각 스팟별로 차 또는 도보로 이동거리를 알려주고 스팟의 대표 사진도 2장의 링크도 추가해줘.")
        return travelPlan.toString()
    }
}

data class Interests(
    val food: Boolean?,
    val shopping: Boolean?,
    val hotSpots: Boolean?,
    val historicalSites: Boolean?,
    val withChildren: Boolean?,
    val museums: Boolean?,
    val activities: Boolean?,
    val arts: Boolean?,
    val zoo: Boolean?,
    val amusementPark: Boolean?
) {
    fun toInterestList(): List<String> {
        return mapOf(
            "맛집" to food,
            "쇼핑" to shopping,
            "핫플" to hotSpots,
            "역사적인 곳" to historicalSites,
            "아이와 함께" to withChildren,
            "박물관/미술관" to museums,
            "엑티비티" to activities,
            "예술/문화" to arts,
            "동물원" to zoo,
            "놀이동산" to amusementPark
        ).mapNotNull { (interest, isInterested) ->
            if (isInterested == true) interest else null
        }
    }
}