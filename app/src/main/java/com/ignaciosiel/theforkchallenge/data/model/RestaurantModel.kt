package com.ignaciosiel.theforkchallenge.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantResult(
    val data: RestaurantDetails
)

/**
 * This class is not ideal.
 * You can create custom adapters for GSON serialization / deserialization and clean this DTO.
 */
data class RestaurantDetails(
    @SerializedName(value="restaurant_id", alternate=["id_restaurant"]) val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val picsMain: Picture?,
    val picsDiaporama : List<Picture>,
    @SerializedName(value="specialty", alternate=["speciality"]) val speciality: String,
    val avgRate: Double?, //Optional to show rate
    val rateCount: Long?, //Optional for avgRate
    val rateDistinction: String?, //Optional
    val description: String,
    val hourOpen: String,
    @SerializedName("card_start_1") val menuStart1: String,
    @SerializedName("card_start_2") val menuStart2: String,
    @SerializedName("card_start_3") val menuStart3: String,
    @SerializedName("card_main_1") val menuMain1: String,
    @SerializedName("card_main_2") val menuMain2: String,
    @SerializedName("card_main_3") val menuMain3: String,
    @SerializedName("card_dessert_1") val menuDessert1: String,
    @SerializedName("card_dessert_2") val menuDessert2: String,
    @SerializedName("card_dessert_3") val menuDessert3: String,
    @SerializedName("trip_advisor_avg_rating") val tripAdvisorRating: Double?,
    @SerializedName("trip_advisor_review_count") val tripAdvisorReviewCount: Int?,
    val currencyCode: String,
    val cardPrice: Long
)
data class Picture(
    @SerializedName("612x344") val url: String,
    val label: String
)
