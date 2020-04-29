package com.vinidsl.redditsample.network.response

import com.vinidsl.redditsample.model.RedditEntry


class TopEntriesResponse {

    var data : ReditData? = null

    inner class ReditData {
        var children : List<RedditEntry>? = null
        var after : String? = null
        var before : String? = null
    }
}