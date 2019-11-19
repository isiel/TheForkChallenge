package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed
import com.ignaciosiel.theforkchallenge.mvp.BasePresenter
import com.ignaciosiel.theforkchallenge.mvp.BaseView

class RestaurantListPresenter(var listView: RestaurantListView?,
                              var listRepository: RestaurantListRepository): BasePresenter {

    var lastSearchedRestaurants: ArrayList<RestaurantDetailsResumed>? = null

    fun getRestaurants() {
        listView?.showLoading()

        if (lastSearchedRestaurants == null) {
            lastSearchedRestaurants = listRepository.getRestaurants()
        }

        listView?.hideLoading()
        listView?.showRestaurants(lastSearchedRestaurants!!)
    }

    override fun attachView(view: BaseView) {
        listView = view as RestaurantListView
    }

    override fun detachView() {
        listView = null
    }

}