package me.sipethon.travel.infrastructure

import me.sipethon.travel.domain.TravelPlanKeyword
import org.springframework.data.jpa.repository.JpaRepository

interface TravelPlanKeywordRepository: JpaRepository<TravelPlanKeyword, Long> {
    fun findAllByTravelPlanIdIn(travelPlainIds: List<Long>): List<TravelPlanKeyword>
}
