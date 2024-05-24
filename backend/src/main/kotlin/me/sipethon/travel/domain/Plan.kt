package me.sipethon.travel.domain

data class Plan(
    val title: String,
    val thumbnail: String,
    val budget: Budget,
    val travelPlan: List<PlanDetail>
) {
    companion object {
        fun dummy() = Plan(
            title = "제목",
            thumbnail = "https://example.com/thumbnail.jpg",
            budget = Budget(
                accommodationMain = "중저가 호텔추천",
                accommodationSub = "1박 10~20만원",
                transportation = "50만원~100만원"
            ),
            travelPlan = listOf(
                PlanDetail(
                    day = 1,
                    schedule = listOf(
                        Schedule(
                            title = "오전",
                            activities = listOf(
                                Activity(
                                    title = "마리나 베이 샌즈",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://example.com/marina-bay-sands.jpg",
                                )
                            )
                        ),
                        Schedule(
                            title = "이동",
                            activities = listOf(
                                Activity(
                                    title = "도보로 10분",
                                    category = null,
                                    type = ActivityType.TEXT,
                                    imgUrl = null
                                )
                            )
                        ),
                        Schedule(
                            title = "오후",
                            activities = listOf(
                                Activity(
                                    title = "가디언즈 오브 갤럭시",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://example.com/guardians-of-the-galaxy.jpg",
                                )
                            )
                        ),
                    )
                ),
                PlanDetail(
                    day = 2,
                    schedule = listOf(
                        Schedule(
                            title = "가디언즈 오브 갤럭시",
                            activities = listOf(
                                Activity(
                                    title = "가디언즈 오브 갤럭시",
                                    category = "관광지",
                                    type = ActivityType.COMPLEX,
                                    imgUrl = "https://example.com/guardians-of-the-galaxy.jpg",
                                )
                            )
                        ),
                    )
                ),
            )
        )
    }
}
