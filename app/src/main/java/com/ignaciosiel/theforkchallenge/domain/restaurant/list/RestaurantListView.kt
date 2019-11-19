package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed
import com.ignaciosiel.theforkchallenge.mvp.BaseView

interface RestaurantListView: BaseView {
    fun showRestaurants(restaurants: ArrayList<RestaurantDetailsResumed>)
}