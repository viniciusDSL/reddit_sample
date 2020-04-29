package com.vinidsl.redditsample.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vinidsl.redditsample.datasource.RedditEntrySourceFactory
import com.vinidsl.redditsample.model.RedditEntry

class RedditRepository {

    private val INIT_LOAD_SIZE = 10
    private val PAGE_SIZE = 10

    private val factory = RedditEntrySourceFactory()
    private val config = PagedList.Config.Builder().setEnablePlaceholders(false)
        .setInitialLoadSizeHint(INIT_LOAD_SIZE)
        .setPageSize(PAGE_SIZE).build()

    fun instanceLivePage() : LiveData<PagedList<RedditEntry>> {
        //for now just from the server
        return LivePagedListBuilder(factory, config).build()
    }

}