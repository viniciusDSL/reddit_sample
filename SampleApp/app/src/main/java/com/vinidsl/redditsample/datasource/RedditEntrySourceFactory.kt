package com.vinidsl.redditsample.datasource

import androidx.paging.DataSource
import com.vinidsl.redditsample.model.RedditEntry

class RedditEntrySourceFactory : DataSource.Factory<String, RedditEntry>() {

    override fun create(): RedditEntryDataSource = RedditEntryDataSource()
}