package me.sipethon.travel.interfaces.response

import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.domain.TravelPlanKeyword

data class TravelPlanResponse(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val userInput: UserInputResponse,
    val budget: BudgetResponse,
    val travelPlan: List<TravelPlanDetailResponse>,
    val isBookmarked: Boolean
) {
    constructor(travelPlan: TravelPlan, travelPlanKeywords: List<TravelPlanKeyword>) : this(
        id = travelPlan.id,
        title = travelPlan.plan.title,
        thumbnail = travelPlan.thumbnail,
        userInput = UserInputResponse(
            location = travelPlan.location,
            duration = travelPlan.duration,
            people = travelPlan.people,
            groupType = travelPlan.groupType,
            budget = travelPlan.budget,
            keywords = travelPlanKeywords.map { it.keyword }
        ),
        budget = BudgetResponse(
            accommodationMain = travelPlan.plan.budget.accommodationMain,
            accommodationSub = travelPlan.plan.budget.accommodationSub,
            transportation = travelPlan.plan.budget.transportation
        ),
        travelPlan = travelPlan.plan.travelPlan.map {
            TravelPlanDetailResponse(
                day = it.day,
                schedule = it.schedule.map { s ->
                    ScheduleResponse(
                        title = s.title,
                        activities = s.activities.map { a ->
                            ActivityResponse(
                                title = a.title,
                                category = a.category,
                                type = a.type,
                                imgUrl = a.imgUrl
                            )
                        }
                    )
                }
            )
        },
        isBookmarked = travelPlan.isBookmarked
    )
}
