package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import com.ignaciosiel.theforkchallenge.mvp.BasePresenter
import com.ignaciosiel.theforkchallenge.mvp.BaseView

class RestaurantDetailsPresenter(var detailsView: RestaurantDetailsView?,
                                 var detailsRepository: RestaurantDetailsRepository): BasePresenter,
    RestaurantDetailsRepository.DetailsListener {

    var lastSearchedRestaurant: RestaurantDetails? = null

    fun getRestaurantDetails(restaurantId: String) {
        detailsView?.showLoading()

        if (lastSearchedRestaurant == null) {
            detailsRepository.getRestaurantDetails(restaurantId, this)
        } else {
            returnRestaurantDetails(lastSearchedRestaurant!!)
        }
    }

    override fun attachView(view: BaseView) {
        detailsView = view as RestaurantDetailsView
    }

    override fun detachView() {
        detailsView = null
    }

    override fun onError() {
        detailsView?.hideLoading()
        detailsView?.showError()
    }

    override fun onSuccess(restaurantDetails: RestaurantDetails) {
        lastSearchedRestaurant = restaurantDetails
        returnRestaurantDetails(restaurantDetails)
    }

    fun returnRestaurantDetails(restaurantDetails: RestaurantDetails) {
        detailsView?.hideLoading()
        detailsView?.showRestaurantDetails(restaurantDetails)
    }
}