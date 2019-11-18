package com.ignaciosiel.theforkchallenge.data.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.ignaciosiel.theforkchallenge.data.model.RestaurantResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheForkApi {

    //http://api.lafourchette.com/api?%20key=IPHONEPRODEDCRFV&method=restaurant_get_info&id_restaurant=6861

    @GET("api")
    fun getRestaurantDetails(@Query("method") method: String = "restaurant_get_info",
                     @Query("id_restaurant") restaurantId: String,
                     @Query("key") key: String = KEY): Observable<RestaurantResult>

    companion object Factory {
        val BASE_URL = "http://api.lafourchette.com/"
        private val KEY = "IPHONEPRODEDCRFV"

        fun create(): TheForkApi {

            val gson = GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(TheForkApi::class.java)
        }
    }
}