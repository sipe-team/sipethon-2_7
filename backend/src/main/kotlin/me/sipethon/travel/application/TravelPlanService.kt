package me.sipethon.travel.application

import me.sipethon.travel.domain.ActivityType
import me.sipethon.travel.domain.Plan
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
    private val imageSearchService: ImageSearchService,
) {
    @Transactional
    fun createTravelPlan(
        userId: Long,
        travelPlanRequest: TravelPlanRequest
    ): Pair<TravelPlan, List<TravelPlanKeyword>> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("User not found")

        val travelPlanString = travelPlanRequest.toTravelPlan()
        val generatedPlan = generateTravelPlan(travelPlanRequest.location, travelPlanString)

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

    private fun generateTravelPlan(location: String, travelPlanString: String): Plan {
        val generatedPlan = openAIService.generateTravelPlan(travelPlanString)

        val titles = generatedPlan.travelPlan
            .map { it.schedule }
            .map { schedule -> schedule.flatMap { it.titles } }
        val imageMap = imageSearchService.search(titles.flatten() + location)
        generatedPlan.travelPlan.forEach { planDetail ->
            planDetail.schedule.forEach { schedule ->
                schedule.activities.filter { activity -> activity.type == ActivityType.COMPLEX }
                    .forEach { activity ->
                        activity.imgUrl = imageMap[activity.title]
                    }
            }
        }

        generatedPlan.thumbnail = imageMap[location]!!
        return generatedPlan
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
