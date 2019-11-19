package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignaciosiel.theforkchallenge.R
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed
import com.ignaciosiel.theforkchallenge.domain.restaurant.details.RestaurantDetailsActivity
import kotlinx.android.synthetic.main.activity_restaurant_list.*


class RestaurantListActivity : AppCompatActivity(), RestaurantListView, OnItemClickListener {

    var presenter: RestaurantListPresenter? = null
    lateinit var adapter: RestaurantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ignaciosiel.theforkchallenge.R.layout.activity_restaurant_list)
        attachPresenter()
        configureRecyclerView()
        getRestaurants()
    }

    private fun configureRecyclerView() {
        adapter = RestaurantListAdapter(arrayListOf(), this, this)

        restaurants_recycler_view.adapter = adapter
        restaurants_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    fun getRestaurants() {
        presenter?.getRestaurants()
    }

    override fun showRestaurants(restaurants: ArrayList<RestaurantDetailsResumed>) {
        adapter.addAll(restaurants)
    }

    override fun onItemClick(id: Long) {
        val intent = Intent(this, RestaurantDetailsActivity::class.java)
        intent.putExtra(RestaurantDetailsActivity.RESTAURANT_ID_KEY, id)
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun attachPresenter() {
        presenter = lastCustomNonConfigurationInstance as RestaurantListPresenter?
        if (presenter == null) {
            presenter = RestaurantListPresenter(this, RestaurantListRepository())
        }
        presenter?.attachView(this)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}
