package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.ignaciosiel.theforkchallenge.R
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import kotlinx.android.synthetic.main.activity_restaurant_details.*
import kotlinx.android.synthetic.main.restaurant_info_layout.*
import kotlinx.android.synthetic.main.restaurant_menu_layout.*
import kotlinx.android.synthetic.main.restaurant_rating_layout.*
import kotlinx.android.synthetic.main.restaurant_trip_advisor_rating_layout.*

class RestaurantDetailsActivity : AppCompatActivity(), RestaurantDetailsView {

    private var presenter: RestaurantDetailsPresenter? = null
    lateinit var restaurantName : String

    companion object {
        const val RESTAURANT_ID_KEY = "RESTAURANT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        attachPresenter()
        setupToolbar()
        getRestaurantDetails()
    }

    private fun setupToolbar() {
        toolbar.navigationIcon = resources.getDrawable(R.mipmap.ic_navigation_back, theme)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar_layout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.ralewayx_regular))
        toolbar_layout.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.ralewayx_regular))
    }

    private fun getRestaurantDetails() {
        intent?.extras?.get(RESTAURANT_ID_KEY)?.apply {
            presenter?.getRestaurantDetails(this.toString())
        }
    }

    override fun showRestaurantDetails(restaurantDetails: RestaurantDetails) {
        restaurantName = restaurantDetails.name
        showImagesAndName(restaurantDetails)
        showRestaurantInfo(restaurantDetails)
        showMenu(restaurantDetails)
        showUserRating(restaurantDetails)
        showTripAdvisorRating(restaurantDetails)
        showReservationButton()
    }

    override fun showImagesAndName(restaurantDetails: RestaurantDetails) {
        //TODO: set image placeholder
        restaurantDetails.picsMain?.url.apply {
            restaurant_image.setImageURI(this)
        }
        toolbar_layout.title = restaurantDetails.name
    }

    override fun showRestaurantInfo(restaurantDetails: RestaurantDetails) {
        address.text = resources.getString(R.string.address,
            restaurantDetails.address, restaurantDetails.city)
        food_type.text = restaurantDetails.speciality
        avg_price.text = resources.getString(R.string.avg_price,
            restaurantDetails.cardPrice.toString(), restaurantDetails.currencyCode)
        restaurantDetails.avgRate?.apply {
            info_user_rating.visibility = View.VISIBLE

            info_user_rating.text = getUserRatingFormatted(this)

            restaurantDetails.rateCount?.apply {
                info_user_rate_count.visibility = View.VISIBLE
                info_user_rate_count.text = resources.getString(R.string.user_rate_count, this.toString())
            }
        }
    }

    private fun getUserRatingFormatted(rating: Double): SpannableString {
        val ratingString = resources.getString(R.string.user_rating, rating.toString())
        val spannableString = SpannableString(ratingString)
        spannableString.setSpan(RelativeSizeSpan(2f), 0, 3, 0)
        return spannableString
    }

    override fun showMenu(restaurantDetails: RestaurantDetails) {
        showMenuStart(restaurantDetails.menuStart1,
            restaurantDetails.menuStart2,
            restaurantDetails.menuStart3)
        showMenuMain(restaurantDetails.menuMain1,
            restaurantDetails.menuMain2,
            restaurantDetails.menuMain3)
        showMenuDesserts(restaurantDetails.menuDessert1,
            restaurantDetails.menuDessert2,
            restaurantDetails.menuDessert3)
    }

    private fun showMenuStart(vararg start: String) {
        menu_start_first.text = start[0]
        menu_start_second.text = start[1]
        menu_start_third.text = start[2]
    }

    private fun showMenuMain(vararg main: String) {
        menu_main_first.text = main[0]
        menu_main_second.text = main[1]
        menu_main_third.text = main[2]
    }

    private fun showMenuDesserts(vararg dessert: String) {
        menu_dessert_first.text = dessert[0]
        menu_dessert_second.text = dessert[1]
        menu_dessert_third.text = dessert[2]
    }

    override fun showUserRating(restaurantDetails: RestaurantDetails) {
        if (restaurantDetails.avgRate != null || restaurantDetails.rateDistinction != null) {
            restaurant_user_rate_container.visibility = View.VISIBLE

            restaurantDetails.avgRate?.apply {
                rating_user_rating.visibility = View.VISIBLE
                rating_user_rating.text = getUserRatingFormatted(this)

                restaurantDetails.rateCount?.apply {
                    rating_user_count.visibility = View.VISIBLE
                    rating_user_count.text = resources.getString(R.string.user_rate_count, this.toString())
                }
            }

            restaurantDetails.rateDistinction?.apply {
                rating_rate_distinction.visibility = View.VISIBLE
                rating_rate_distinction.text = this
            }
        }
    }

    override fun showTripAdvisorRating(restaurantDetails: RestaurantDetails) {
        trip_advisor_rating.rating = restaurantDetails.tripAdvisorRating!!.toFloat()
        trip_advisor_review_count.text = resources.getString(R.string.user_rate_count,
            restaurantDetails.tripAdvisorReviewCount.toString())
    }

    override fun attachPresenter() {
        presenter = lastCustomNonConfigurationInstance as RestaurantDetailsPresenter?
        if (presenter == null) {
            presenter = RestaurantDetailsPresenter(this, RestaurantDetailsRepository())
        }
        presenter?.attachView(this)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    fun makeAReservation(view: View) {
        Toast.makeText(this,
            resources.getString(R.string.reservation_details,
                restaurantName), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError() {
        Toast.makeText(this,
            resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showReservationButton() {
        make_reservation_button.isEnabled = true
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}
