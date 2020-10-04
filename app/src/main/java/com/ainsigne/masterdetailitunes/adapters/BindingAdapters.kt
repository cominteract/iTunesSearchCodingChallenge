package com.ainsigne.masterdetailitunes.adapters

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ainsigne.masterdetailitunes.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.util.*

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
 * Binds random color to a view
 */
@BindingAdapter("bindRandomColor")
fun bindRandomColor(view: View, imageUrl: String?) {
    val r = Random()
    val num: Int = r.nextInt(10 - 1) + 1
    when (num) {
        0 -> {
            view.setBackgroundColor(Color.RED)
        }
        1 -> {
            view.setBackgroundColor(Color.BLUE)
        }
        2 -> {
            view.setBackgroundColor(Color.GREEN)
        }
        3 -> {
            view.setBackgroundColor(Color.DKGRAY)
        }
        4 -> {
            view.setBackgroundColor(Color.BLACK)
        }
        5 -> {
            view.setBackgroundColor(Color.YELLOW)
        }
        6 -> {
            view.setBackgroundColor(Color.MAGENTA)
        }
        7 -> {
            view.setBackgroundColor(Color.CYAN)
        }
        8 -> {
            view.setBackgroundColor(Color.LTGRAY)
        }
        9 -> {
            view.setBackgroundColor(Color.RED)
        }
        else -> {
            view.setBackgroundColor(Color.BLACK)
        }
    }

}


/**
 * Binds the image from the url to the imageview as a circle
 */
@BindingAdapter("normalImageFromUrl")
fun bindNormalImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_background)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
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
