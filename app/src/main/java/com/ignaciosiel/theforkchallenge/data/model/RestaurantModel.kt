package com.ignaciosiel.theforkchallenge.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    val data: RestaurantDetails
)

/**
 * This class is not ideal.
 * You can create a custom adapter for GSON serialization / deserialization and clean this DTO.
 */
data class RestaurantDetails(@SerializedName(value="restaurant_id", alternate=["id_restaurant"])
                             val id: Long,
                             val name: String) {
    lateinit var address: String
    lateinit var city: String
    var picsMain: Picture? = null
    var picsDiaporama : ArrayList<Picture>? = null
    @SerializedName(value="specialty", alternate=["speciality"]) lateinit var speciality: String
    var avgRate: Double? = null
    var rateCount: Long? = null
    var rateDistinction: String? = null
    @SerializedName("card_start_1") lateinit var menuStart1: String
    @SerializedName("card_start_2") lateinit var menuStart2: String
    @SerializedName("card_start_3") lateinit var menuStart3: String
    @SerializedName("card_main_1") lateinit var menuMain1: String
    @SerializedName("card_main_2") lateinit var menuMain2: String
    @SerializedName("card_main_3") lateinit var menuMain3: String
    @SerializedName("card_dessert_1") lateinit var menuDessert1: String
    @SerializedName("card_dessert_2") lateinit var menuDessert2: String
    @SerializedName("card_dessert_3") lateinit var menuDessert3: String
    @SerializedName("trip_advisor_avg_rating") var tripAdvisorRating: Double? = null
    @SerializedName("trip_advisor_review_count") var tripAdvisorReviewCount: Int? = null
    lateinit var currencyCode: String
    var cardPrice: Long? = null
}

data class Picture(
    @SerializedName("612x344") val url: String,
    val label: String
)

data class RestaurantDetailsResumed(val id: Long, val name: String, val picture: String, val specialty: String)
