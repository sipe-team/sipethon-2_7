package me.sipethon.travel.infrastructure

import me.sipethon.travel.domain.TravelPlan
import org.springframework.data.jpa.repository.JpaRepository

interface TravelPlanRepository : JpaRepository<TravelPlan, Long>
