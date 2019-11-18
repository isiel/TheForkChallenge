package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ignaciosiel.theforkchallenge.R
import com.ignaciosiel.theforkchallenge.domain.restaurant.details.RestaurantDetailsActivity

class RestaurantListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToDetails(view: View) {
        //TODO: with restaurant click go to activity.

        val intent = Intent(this, RestaurantDetailsActivity::class.java)
        intent.putExtra(RestaurantDetailsActivity.RESTAURANT_ID_KEY, 40370L)
        startActivity(intent)
    }
}
