package com.vinidsl.redditsample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditEntry (

    var title : String? = null,

    var author : String? = null,

    var thumbnail : String? = null,

    var commentsCount : Int? = null,

    var created : Double? = null,

    var url : String? = null,

    var name: String? = null

) : Parcelable