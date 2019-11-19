package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed

class RestaurantListRepository {

    fun getRestaurants(): ArrayList<RestaurantDetailsResumed> {
        return arrayListOf(
            RestaurantDetailsResumed(6861L,
                "Kathmandu Coffee",
                "https://u.tfstatic.com/restaurant_photos/861/6861/169/612/katmandou-cafe-vue-de-la-salle-94087.jpg" ,
                "Indian"),
            RestaurantDetailsResumed(40370L,
                "L'épicerie Saint-Sabin",
                "https://u.tfstatic.com/restaurant_photos/370/40370/169/612/l-epicerie-saint-sabin-cote-cave-c351b.jpg",
                "Français"),
            RestaurantDetailsResumed(16409L,
                "Angeethi",
                "https://u.tfstatic.com/restaurant_photos/409/16409/169/612/Devanture.jpg",
                "Indien"),
            RestaurantDetailsResumed(14163L,
                "Momiji",
                "https://u.tfstatic.com/restaurant_photos/163/14163/169/612/momiji-restaurant-japonais-design-82eb3.jpg",
                "Japanese")
        )
    }
}