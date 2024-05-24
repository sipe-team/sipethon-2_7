package me.sipethon.travel.interfaces

import me.sipethon.travel.application.TravelPlanService
import me.sipethon.travel.common.auth.LoginUser
import me.sipethon.travel.domain.TravelPlanPreferences
import org.springframework.web.bind.annotation.*

@RestController
class TravelPlanController(
    private val travelPlanService: TravelPlanService,
) {

    @PostMapping("/travel-plan")
    fun createTravelPlan(
        @LoginUser user: Long,
        @RequestBody preferences: TravelPlanPreferences,
    ): Long {
        return travelPlanService.createTravelPlan(preferences)
    }
}
