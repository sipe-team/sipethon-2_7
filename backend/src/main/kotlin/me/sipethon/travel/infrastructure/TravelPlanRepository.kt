package me.spiethon.travel.infrastructure

import me.spiethon.travel.domain.TravelPlan
import org.springframework.data.jpa.repository.JpaRepository

interface TravelPlanRepository : JpaRepository<TravelPlan, Long>
