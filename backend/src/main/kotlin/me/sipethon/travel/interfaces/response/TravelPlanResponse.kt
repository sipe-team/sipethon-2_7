package me.sipethon.travel.interfaces.response

data class TravelPlanResponse(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val userInput: UserInputResponse,
    val budget: BudgetResponse,
    val travelPlan: List<TravelPlanDetailResponse>
)
