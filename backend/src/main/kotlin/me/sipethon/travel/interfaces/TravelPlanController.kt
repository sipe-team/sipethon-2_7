package me.sipethon.travel.interfaces

import me.sipethon.travel.application.TravelPlanService
import me.sipethon.travel.common.auth.LoginUser
import me.sipethon.travel.interfaces.request.TravelPlanRequest
import me.sipethon.travel.interfaces.response.BookmarkResponse
import me.sipethon.travel.interfaces.response.SearchHistoryResponse
import me.sipethon.travel.interfaces.response.TravelPlanResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TravelPlanController(
    private val travelPlanService: TravelPlanService,
) {
    @PostMapping("/travel-plan")
    fun createTravelPlan(
        @LoginUser userId: Long,
        @RequestBody preferences: TravelPlanRequest,
    ): TravelPlanResponse {
        travelPlanService.createTravelPlan(userId, preferences)
        TODO()
    }

    @GetMapping("/search-history")
    fun searchHistory(
        @LoginUser userId: Long,
        @RequestParam onlyBookmarked: Boolean
    ): SearchHistoryResponse {
        TODO()
    }

    @PostMapping("/bookmark/{travelPlanId}")
    fun bookmark(
        @LoginUser userId: Long,
        @PathVariable travelPlanId: Long
    ): BookmarkResponse {
        val travelPlan = travelPlanService.bookmark(userId, travelPlanId)
        return BookmarkResponse(travelPlan.id, travelPlan.isBookmarked)
    }

    @GetMapping("/travel-plan/{travelPlanId}")
    fun getTravelPlan(
        @LoginUser userId: Long,
        @PathVariable travelPlanId: Long
    ): TravelPlanResponse {
        TODO()
    }
}
