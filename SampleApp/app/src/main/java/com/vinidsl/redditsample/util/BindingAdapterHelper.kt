package com.vinidsl.redditsample.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vinidsl.redditsample.storage.SharedPreferencesManager
import org.ocpsoft.prettytime.PrettyTime
import java.sql.Date
import java.util.*

object BindingAdapterHelper {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.context)
            .load(imageURL)
            .into(imageView)
    }

    @BindingAdapter("timeAgo")
    @JvmStatic
    fun calculateTime(textView: TextView, timeUnix: Double?) {
        timeUnix?.let {
            // convert to Date
            val date = Date(it.toLong() * 1000L)

            val prettyTime = PrettyTime(Locale.getDefault())
            textView.text = prettyTime.format(Date(date.time))
        }
    }

    @BindingAdapter("readStatus")
    @JvmStatic
    fun verifyReadStatus(view: View, redditEntryName: String) {
       view.visibility = if (SharedPreferencesManager.verifyRedditEntry(view.context, redditEntryName)){
           View.INVISIBLE
       } else {
           View.VISIBLE
       }
    }
}