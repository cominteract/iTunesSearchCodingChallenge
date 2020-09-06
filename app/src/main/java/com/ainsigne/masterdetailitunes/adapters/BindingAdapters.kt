package com.ainsigne.masterdetailitunes.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ainsigne.masterdetailitunes.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Data Binding adapters specific to the app.
 */

/**
 * Binds the image from the url to the imageview
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_audiotrack_24px)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

/**
 * Binds the text label with given track price
 */
@BindingAdapter("trackPrice")
fun bindTrackPrice(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.text = String.format("%s %s", view.context.getString(R.string.track_price), text)
    }
}

/**
 * Binds the text label with given track genre
 */
@BindingAdapter("trackGenre")
fun bindTrackGenre(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.text = String.format("%s %s", view.context.getString(R.string.track_genre), text)
    }
}

/**
 * Binds the image from the url to the imageview as a circle
 */
@BindingAdapter("circleImageFromUrl")
fun bindCircleImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .circleCrop()
            .placeholder(R.drawable.ic_audiotrack_24px)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

/**
 * TODO : unused
 */
@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
