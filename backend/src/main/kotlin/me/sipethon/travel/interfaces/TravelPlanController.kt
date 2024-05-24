package me.spiethon.travel.interfaces

import me.spiethon.travel.application.TravelPlanService
import me.spiethon.travel.domain.TravelPlanPreferences
import org.springframework.web.bind.annotation.*

@RestController
class TravelPlanController(
    private val travelPlanService: TravelPlanService,
) {

    @PostMapping("/travel-plan")
    fun createTravelPlan(
        @RequestBody preferences: TravelPlanPreferences,
    ): Long {
        return travelPlanService.createTravelPlan(preferences)
    }
}