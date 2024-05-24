package me.sipethon.travel.application

import me.sipethon.travel.domain.Activity
import me.sipethon.travel.domain.ActivityType
import me.sipethon.travel.domain.Budget
import me.sipethon.travel.domain.Plan
import me.sipethon.travel.domain.PlanDetail
import me.sipethon.travel.domain.Schedule
import me.sipethon.travel.domain.TravelPlan
import me.sipethon.travel.domain.TravelPlanKeyword
import me.sipethon.travel.infrastructure.TravelPlanKeywordRepository
import me.sipethon.travel.infrastructure.TravelPlanRepository
import me.sipethon.travel.infrastructure.UserRepository
import me.sipethon.travel.interfaces.request.TravelPlanRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class TravelPlanService(
    private val travelPlanRepository: TravelPlanRepository,
    private val userRepository: UserRepository,
    private val travelPlanKeywordRepository: TravelPlanKeywordRepository,
    private val openAIService: OpenAIService,
) {
    @Transactional
    fun createTravelPlan(userId: Long, travelPlanRequest: TravelPlanRequest): Pair<TravelPlan, List<TravelPlanKeyword>> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("User not found")

        val travelPlanString = travelPlanRequest.toTravelPlan()
        val generatedPlan = Plan.dummy()

        val travelPlan = travelPlanRepository.save(
            TravelPlan(
                userId = user.id,
                plan = generatedPlan,
                thumbnail = generatedPlan.thumbnail,
                location = travelPlanRequest.location,
                duration = travelPlanRequest.duration,
                people = travelPlanRequest.people,
                budget = travelPlanRequest.budget,
                groupType = travelPlanRequest.groupType,
            )
        )
        val travelPlanKeywords = travelPlanRequest.keywords.map {
            TravelPlanKeyword(
                travelPlanId = travelPlan.id,
                keyword = it
            )
        }
        travelPlanKeywordRepository.saveAll(travelPlanKeywords)

        return travelPlan to travelPlanKeywords
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

    fun searchHistory(userId: Long, onlyBookmarked: Boolean): Map<TravelPlan, List<TravelPlanKeyword>> {
        val travelPlans = if (onlyBookmarked) {
            travelPlanRepository.findAllByUserIdAndIsBookmarkedTrueOrderByCreatedAtDesc(userId)
        } else {
            travelPlanRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
        }
        val travelPlanKeywords = travelPlanKeywordRepository.findAllByTravelPlanIdIn(travelPlans.map { it.id })

        return travelPlans.associateWith { travelPlan ->
            travelPlanKeywords.filter { it.travelPlanId == travelPlan.id }
        }
    }

    fun findTravelPlan(userId: Long, travelPlanId: Long): Pair<TravelPlan, List<TravelPlanKeyword>> {
        val travelPlan = travelPlanRepository.findByIdOrNull(travelPlanId)
            ?: throw RuntimeException("TravelPlan not found")
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("User not found")
        if (travelPlan.userId != user.id) {
            throw RuntimeException("TravelPlan does not belong to user")
        }
        val travelPlanKeywords = travelPlanKeywordRepository.findAllByTravelPlanIdIn(listOf(travelPlan.id))

        return travelPlan to travelPlanKeywords
    }
}
