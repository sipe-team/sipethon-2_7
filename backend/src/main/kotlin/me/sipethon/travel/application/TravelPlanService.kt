package me.sipethon.travel.application

import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.domain.TravelPlanPreferences
import me.sipethon.travel.infrastructure.TravelPlanRepository
import org.springframework.stereotype.Service

@Service
class TravelPlanService(
    private val travelPlanRepository: TravelPlanRepository,
    private val openAIService: OpenAIService,
) {

    fun createTravelPlan(preferences: TravelPlanPreferences) : Long {
        val travelPlanString = preferences.toTravelPlan()

        // Use the OpenAi Api for creating AI Travel Plan
        val generatedPlan = openAIService.generateTravelPlan(travelPlanString)

        // save the generated travel plan and return the results
        val travelPlan = travelPlanRepository.save(TravelPlan(null, generatedPlan))
        return travelPlan.id?: throw RuntimeException("TravelPlan creation Failed")
    }
}