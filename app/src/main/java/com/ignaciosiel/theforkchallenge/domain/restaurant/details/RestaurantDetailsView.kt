package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import com.ignaciosiel.theforkchallenge.mvp.BaseView

interface RestaurantDetailsView: BaseView {
    fun showRestaurantDetails(restaurantDetails: RestaurantDetails)
    fun showImagesAndName(restaurantDetails: RestaurantDetails)
    fun showRestaurantInfo(restaurantDetails: RestaurantDetails)
    fun showMenu(restaurantDetails: RestaurantDetails)
    fun showUserRating(restaurantDetails: RestaurantDetails)
    fun showTripAdvisorRating(restaurantDetails: RestaurantDetails)
    fun showReservationButton()
    fun showError()
}