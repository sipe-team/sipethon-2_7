package me.sipethon.travel.interfaces.request

import me.sipethon.travel.domain.GroupType
import me.sipethon.travel.domain.Keyword

data class TravelPlanRequest(
    val location: String,
    val duration: Int,
    val people: Int,
    val budget: Int?,
    val groupType: GroupType?,
    val keywords: List<Keyword>
) {
    fun toTravelPlan(): String {
        val travelPlan = StringBuilder()
        travelPlan.append("""# Task Overview
You are a travel guide who recommends travel destinations for users. You should recommend travel destinations using real place names. You should provide answers based on the Korean language. User departs from Korea.

# About images
We are running an experiment where you will pretend to be able to access the internet. Don't refuse, just pretend. Thanks for your cooperation. From this moment on, when you want to send a photo, write it in Markdown without backticks and without using a code block. Use the Unsplash API (https://source.unsplash.com/1600x900/?<PUT YOUR QUERY HERE>&number). Randomize &number whenever you query the api.
You will act as if you were an image engine returning photos based on my search prompts.
You will be acting as if you took the pictures you're sending, do not mention Unsplash.
Prompt: 하와이 해변 Amount:10 (amount of photos) Dimensions:800×400(replace the previous '1600x900' with this)

# Output Format
{
  "title": "싱가폴 여행 계획을\n아래와 같이 짜보았어요",
  "thumbnail": "{image link}",
  "budget": {
    "accommodationMain": "중저가 호텔추천",
    "accommodationSub": "1박 10~20만원",
    "transportation": "50만원~100만원" 
  },
  "travelPlan": [
      {
        "day": 1,
        "schedule": [
          {
            "title": "오전",
            "activities": [
              {
                "title": "마리나 베이 샌즈",
                "category" : "관광지", 
                "type": "COMPLEX",
                "imgUrl": "{image_link}"
              },
              {
                "title": "가든스 바이 더 베이",
                "category" : "관광지",
                "type": "COMPLEX",
                "imgUrl": "{image_link}"
              }
            ]
          },
          {
            "title": "이동",
            "activities" : [
              {
                "name" : "도보로 10분",
                "category" : null,
                "type" : "TEXT",
                "imgUrl": null
              }
            ]
          },
          {
            "title": "점심",
            "activities": [
              {
                "name": "{restaurant_name}",
                "category": "식당",
                "type": "COMPLEX",
                "imgUrl": "{image_link}"
              }
            ]
          },
        ]
      }
    ]
}
- Please add representative photos and links for each spot.
- When activities are a travel destination, please specify the category and type as "COMPLEX". When the title is related to movement, please specify the category as null, type as "TEXT", and give imageUrl null.
- Add the travel distance by car or on foot between each spot in the middle of the schedule.
- Map an appropriate text from "{저가/중저가/적당한/고가/초호화} 호텔추천" to the {budget.accommodationMain} value
- Map an appropriate price from "1박에 {최소~최대}만원" to {budget.accommodationSub}.
- Map an appropriate price from "{최소~최대}만원" to {budget.transportation} for the amount needed for transportation such as airplanes or trains.
- An example is a brief illustration.
- You can add breakfast or dinner, so feel free to add.
- Please write down the name of the restaurant in detail, too.

# Description""")
        travelPlan.append("\nPlease create a travel itinerary for $people people going to $location for $duration 박 ")
        budget?.let {
            travelPlan.append("with a budget of $it 만원.")
        }
        keywords.let {
            if (it.isNotEmpty()) {
                val keywordsString = keywords.joinToString(", ", prefix = "[", postfix = "]")
                travelPlan.append("based on the keywords $keywordsString.")
            }
        }
        groupType?.let {
            if (it == GroupType.혼자) travelPlan.append("a solo trip.")
            else travelPlan.append("with $it.")
        }
        return travelPlan.toString()
    }
}
