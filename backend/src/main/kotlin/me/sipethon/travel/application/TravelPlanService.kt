package me.sipethon.travel.application

import jakarta.transaction.Transactional
import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.infrastructure.TravelPlanRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TravelPlanService(
    private val travelPlanRepository: TravelPlanRepository,
    private val openAIService: OpenAIService,
) {
    @Transactional
    fun createTravelPlan(travelPlanString: String): Long {
        val generatedPlan = openAIService.generateTravelPlan(travelPlanString)
        val travelPlan = travelPlanRepository.save(TravelPlan(plan = generatedPlan))
        return travelPlan.id
    }

    @Transactional
    fun bookmark(userId: Long, travelPlanId: Long): TravelPlan {
        val travelPlan = travelPlanRepository.findByIdOrNull(travelPlanId)
            ?: throw RuntimeException("TravelPlan not found")

        travelPlan.isBookmarked = !travelPlan.isBookmarked

        return travelPlan
    }
}
