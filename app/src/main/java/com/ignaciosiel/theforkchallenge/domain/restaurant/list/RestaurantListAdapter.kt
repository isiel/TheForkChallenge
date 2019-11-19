package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ignaciosiel.theforkchallenge.R
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed
import kotlinx.android.synthetic.main.restaurant_list_row_layout.view.*

class RestaurantListAdapter(val restaurants: ArrayList<RestaurantDetailsResumed>,
                            val context: Context,
                            val listener: OnItemClickListener)
    :RecyclerView.Adapter<RestaurantListAdapter.RestaurantRowViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantRowViewHolder {
            return RestaurantRowViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.restaurant_list_row_layout, parent, false))
        }

        override fun getItemCount(): Int {
            return restaurants.size
        }

        override fun onBindViewHolder(holder: RestaurantRowViewHolder, position: Int) {
            holder.bind(restaurants[position], listener)
        }

        class RestaurantRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val picture = itemView.restaurant_list_row_picture
            val name = itemView.restaurant_list_row_name
            val specialty = itemView.restaurant_list_row_specialty

            fun bind(restaurant: RestaurantDetailsResumed, listener: OnItemClickListener) {
                picture.setImageURI(restaurant.picture)
                name.text = restaurant.name
                specialty.text = restaurant.specialty
                itemView.setOnClickListener {
                    listener.onItemClick(restaurant.id)
                }
            }
        }

        fun addAll(itemsToAdd: ArrayList<RestaurantDetailsResumed>) {
            restaurants.addAll(itemsToAdd)
            notifyDataSetChanged()
        }
}