package me.sipethon.travel.application

import jakarta.transaction.Transactional
import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.infrastructure.TravelPlanRepository
import me.sipethon.travel.infrastructure.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TravelPlanService(
    private val travelPlanRepository: TravelPlanRepository,
    private val userRepository: UserRepository,
    private val openAIService: OpenAIService,
) {
    @Transactional
    fun createTravelPlan(userId: Long, travelPlanString: String): Long {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("User not found")
        val generatedPlan = openAIService.generateTravelPlan(travelPlanString)
        val travelPlan = travelPlanRepository.save(TravelPlan(userId = user.id, plan = generatedPlan))
        return travelPlan.id
    }

    @Transactional
    fun bookmark(userId: Long, travelPlanId: Long): TravelPlan {
        val travelPlan = travelPlanRepository.findByIdOrNull(travelPlanId)
            ?: throw RuntimeException("TravelPlan not found")
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("User not found")

        if (travelPlan.userId != user.id) {
            throw RuntimeException("TravelPlan does not belong to user")
        }

        travelPlan.isBookmarked = !travelPlan.isBookmarked

        return travelPlan
    }
}
