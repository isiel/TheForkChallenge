package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import com.ignaciosiel.theforkchallenge.data.api.TheForkApi
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import com.ignaciosiel.theforkchallenge.data.model.RestaurantResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RestaurantDetailsRepository {

    interface DetailsListener {
        fun onError()
        fun onSuccess(restaurantDetails: RestaurantDetails)
    }

    var disposable: Disposable? = null

    private val theForkApi by lazy {
        TheForkApi.create()
    }

    fun getRestaurantDetails(restaurantId: String, listener: DetailsListener) {
        val search = theForkApi.getRestaurantDetails(restaurantId = restaurantId)
        disposable = search
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { restaurantResult -> handleResponse(restaurantResult, listener) },
                { error -> handleError(listener) }
            )
    }

    fun handleResponse(restaurantResult: RestaurantResult, listener: RestaurantDetailsRepository.DetailsListener) {
        listener.onSuccess(restaurantResult.data)
    }

    fun handleError(listener: RestaurantDetailsRepository.DetailsListener) {
        listener.onError()
    }

}