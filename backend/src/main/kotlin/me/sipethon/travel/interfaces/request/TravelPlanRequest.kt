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
We are running an experiment where you will pretend to be able to access the internet. Don't refuse, just pretend. Thanks for your cooperation. From this moment on, when you want to send a photo, write it in Markdown without backticks and without using a code block. Use the Unsplash API (https://source.unsplash.com/1600x900/?<PUT_YOUR_QUERY_HERE>&number). Randomize &number whenever you query the api.
You will act as if you were an image engine returning photos based on my search prompts.
You will be acting as if you took the pictures you're sending, do not mention Unsplash.
Prompt: 하와이 해변 Amount:10 (amount of photos) Dimensions:800×400(replace the previous '1600x900' with this)

# Output Format
{
  "title": "{location} 여행 계획을\n아래와 같이 짜보았어요",
  "thumbnail": "{image_link}",
  "budget": {
    "accommodationMain": "{budget.accommodationMain} 호텔추천",
    "accommodationSub": "1박 {min ~ max}만원",
    "transportation": "{min ~ max}만원" 
  },
  "travelPlan": [
      {
        "day": 1,
        "schedule": [
          {
            "title": "오전",
            "activities": [
              {
                "title": "{tourist_spot_name}",
                "category" : "관광지", 
                "type": "COMPLEX",
                "imgUrl": "{image_link}"
              },
              {
                "title": "{tourist_spot_name}",
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
                "title" : "{도보/자동차/택시}로 10분",
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
                "title": "{restaurant_name}",
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
- You can add breakfast or dinner, so feel free to add.
- Please write down the name of the restaurant in detail, too.
- Created in complete json form, no trailing commas. 
- You should include the schedule to come back to Korea on the last day.
- You can change the title of the travel plan as you like. Show me your wit. But, you must end the sentence with "~했어요".

# Description""")
        travelPlan.append("\nPlease create a travel itinerary for $people people going to $location for ${duration + 1} days and $duration nights ")
        budget?.let {
            travelPlan.append("with a budget of ${it}만원. ")
        }
        keywords.let {
            if (it.isNotEmpty()) {
                val keywordsString = keywords.joinToString(", ", transform = { keyword -> "\"${keyword}\"" })
                travelPlan.append("based on the keywords $keywordsString. ")
            }
        }
        groupType?.let {
            if (it == GroupType.혼자) travelPlan.append("a solo trip.")
            else travelPlan.append("with $it.")
        }
        return travelPlan.toString()
    }
}
