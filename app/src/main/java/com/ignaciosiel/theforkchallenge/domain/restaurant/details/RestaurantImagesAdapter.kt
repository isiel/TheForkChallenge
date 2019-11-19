package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.ignaciosiel.theforkchallenge.R
import com.ignaciosiel.theforkchallenge.data.model.Picture

class RestaurantImagesAdapter(val images: ArrayList<Picture>, val context: Context): PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.restaurant_image_layout, collection, false) as ViewGroup
        layout.findViewById<SimpleDraweeView>(R.id.restaurant_image).setImageURI(images[position].url)
        collection.addView(layout)
        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

}